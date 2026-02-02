package test.refactoring.movemethod;
import java.io.IOException;
interface Processable {void process() throws IOException;}
strictfp record TargetRecord(int value) implements Processable {class TargetInner {public static void recursiveMethod(int count) throws IOException {if (count <= 0) return;TargetRecord.method(count - 1);}}
public static void method(int count) throws IOException {if (count > 0) {TargetInner.recursiveMethod(count);}}
@Overridepublic void process() throws IOException {}}
public record SourceRecord(String name) {class SourceInner {/**
Javadoc for overloaded method
@param target the target record
@return processed object
@throws IOException if processing fails*/strictfp Object overloadedMethod(TargetRecord target) throws IOException {TargetInner inner = new TargetInner();Object varCall = inner.getValue();
int count = 2;loop: {TargetRecord.method(count);if (count-- <= 0) break loop;}
return SourceRecord.this.name + target.value();}
public Object overloadedMethod() {return null;}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();try {inner.overloadedMethod(new TargetRecord(5));} catch (IOException e) {throw new RuntimeException(e);}}}.run();}
private class TargetInner {String getValue() {return "inner_value";}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3028 {@Testpublic void testOverloadedMethod() throws IOException {SourceRecord source = new SourceRecord("test_");SourceRecord.SourceInner inner = source.new SourceInner();TargetRecord target = new TargetRecord(10);Object result = inner.overloadedMethod(target);assertEquals("test_10", result);}}
package test.refactoring.movemethod;
import java.io.IOException;
protected class TargetClass<T> {protected T value;
public TargetClass(T value) {this.value = value;}
public class TargetInner {
private U innerValue;
public TargetInner(U innerValue) {this.innerValue = innerValue;}
public U getInnerValue() {return innerValue;}
public void setInnerValue(U innerValue) {this.innerValue = innerValue;}}
public T getValue() {return value;}}
protected class SourceClass<T> {static class SourceStaticNested {}
class SourceInner {public final TargetClass<T> process(TargetClass<T> target, String data) throws IOException {// Local inner classclass ValueProcessor {T processValue(T value) {return value;}}ValueProcessor processor = new ValueProcessor();
// Variable callObject varCall = target.value;TargetClass<T>.TargetInner<String> inner = target.new TargetInner<>(data);
// Empty statement;
// Labeled statementprocessLabel: {if (inner.getInnerValue() == null) {throw new IOException("Inner value cannot be null");}inner.setInnerValue(inner.getInnerValue() + "_processed");break processLabel;}
// Super keywords in inner contextclass DerivedInner extends SourceInner {void check() {super.toString();}}new DerivedInner().check();
// Use type parametersT processedValue = processor.processValue(target.getValue());target.value = processedValue;
return target;}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3153 {@Testpublic void testInstanceMethod() throws IOException {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceInner inner = source.new SourceInner();TargetClass<String> target = new TargetClass<>("test");
TargetClass<String> result = inner.process(target, "inner_data");assertEquals("test", result.getValue());assertEquals("inner_data_processed", target.new TargetInner<>("").getInnerValue());}}
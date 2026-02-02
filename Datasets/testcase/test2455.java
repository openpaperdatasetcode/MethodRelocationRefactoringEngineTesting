package test.refactoring.movemethod;
sealed class TargetClass permits SubTarget {public class TargetInner {public String data;
public TargetInner(String data) {this.data = data;}
public String getData() {return data;}}}
final class SubTarget extends TargetClass {}
protected class SourceClass {static class SourceStaticNested {}
class SourceInner {public Object process(TargetClass.TargetInner inner) {// Local inner classclass InnerProcessor {Object handle(TargetClass.TargetInner targetInner) {// Access instance fieldreturn targetInner.data + "_processed";}}
// Variable callObject varCall = inner.getData();
InnerProcessor processor = new InnerProcessor();return processor.handle(inner);}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3159 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new SubTarget();TargetClass.TargetInner targetInner = target.new TargetInner("test");
Object result = inner.process(targetInner);assertEquals("test_processed", result);}}
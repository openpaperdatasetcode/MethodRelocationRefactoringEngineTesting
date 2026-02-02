package test.refactoring.movemethod;
import java.util.function.Function;
class ParentForTarget {}
public class TargetClass extends ParentForTarget {protected String targetField;
class TargetInner {class NestedInner {private int value;
public NestedInner(int value) {this.value = value;}
private String getStringValue() {return String.valueOf(value);}}}
public TargetClass(String field) {this.targetField = field;new Runnable() {@Overridepublic void run() {targetField = targetField + "_init";}}.run();}}
protected class SourceClass {static class StaticNested {}
class SourceInner {private Object process(TargetClass.TargetInner.NestedInner nested) {Object varCall = nested.getStringValue();
// IfStatement feature with transient modifiertransient boolean flag = false;if (nested.value > 0) { // Access to this.field of targetflag = true;}
// Generic method feature with method referenceFunction<TargetClass.TargetInner.NestedInner, Object> func = TargetClass.TargetInner.NestedInner::getStringValue;Object[] array = {func.apply(nested)};
// Regular if statementif (array.length == 1) {varCall = array[0];}
// Throw statement (caught in functional interface)Function<TargetClass.TargetInner.NestedInner, String> errorHandler = n -> {try {if (n.value < 0) {throw new IllegalArgumentException("Negative value");}return ((ParentForTarget) new TargetClass("base")).toString();} catch (Exception e) {return "error";}};
loop: for (int i = 0; i < 2; i++) {if (i == 1) {break loop;}}
return errorHandler.apply(nested);}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3069 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass("test");TargetClass.TargetInner innerTarget = target.new TargetInner();TargetClass.TargetInner.NestedInner nested = innerTarget.new NestedInner(5);
Object result = inner.process(nested);assertNotNull(result);}}
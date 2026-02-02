package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
abstract class SourceClass {private TargetClass targetField = new TargetClass();
class MemberInner {@TestAnnotationprivate int varargsMethod(TargetClass... targets) {variableCall(targets[0]);int val = overloadMethod(5) + overloadMethod("test");NestedHelper helper = new NestedHelper();return helper.process(targets);}
private int overloadMethod(int num) {return num;}
private int overloadMethod(String str) {return str.length();}
private void variableCall(TargetClass target) {TargetClass local = target;int fieldVal = targetField.someField;}}
static class StaticNested {int useTarget(TargetClass target) {return target.someField;}}
class NestedHelper {int process(TargetClass[] targets) {int sum = 0;for (TargetClass t : targets) {sum += t.createLocalInner().getValue();}return sum;}}}
class TargetClass {int someField;
int createLocalInner() {class LocalInner {int getValue() {return someField;}}return new LocalInner().getValue();}}

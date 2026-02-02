package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {class MemberInner {}
@MyAnnotationfinal List<String> normalMethod(TargetClass<? extends Number> target) {class LocalInner {}
// Method types parameter is: keywords (e.g., extends)TargetClass<? super Integer> boundedTarget;
// Access target fieldString fieldVal = target.targetField;
// Varargs method from target using super in property assignmentObject prop = target.new StaticNested().varargsMethod(1, 2, 3);
// Constructor invocationTargetClass.StaticNested nested = new TargetClass.StaticNested();
// If statementif (target != null) {variableCall();}
// MethodInvocation (1 instance)public void methodCall() {target.instanceMethod();}methodCall();
// Uses outer thisSourceClass outerThis = SourceClass.this;
// Override violationclass BadSubclass extends TargetClass.StaticNested {@Overridepublic final void finalMethod() {} // Compile error expected}
return new ArrayList<>();}
private void variableCall() {}}
package target;
public class TargetClass<T> extends ParentClass {String targetField;
static class StaticNested {public final Object varargsMethod(int... args) {return super.parentMethod(args);}
public final void finalMethod() {}}
void instanceMethod() {}}
package target;
class ParentClass {protected Object parentMethod(int... args) {return new Object();}}
package other;
import test.TargetClass;import java.util.function.Supplier;
@MyAnnotationprivate class SourceClass<T> {class MemberInner1 {}class MemberInner2 {TargetClass createTarget() {return new TargetClass(1);}}
protected Object method(TargetClass targetParam, Object... args) {new TargetClass(1);TargetClass newTarget = new MemberInner2().createTarget();
// Private constructor invocation with obj.field=1 in different packageTargetClass.PrivateConstructorWrapper wrapper = new TargetClass.PrivateConstructorWrapper();TargetClass privateTarget = wrapper.createWithField(1);
targetParam.field = args;
Supplier<String> supplier = () -> OtherClass.process(targetParam).m2().m3();return supplier.get();}}
package test;
import java.io.Serializable;
private class TargetClass implements Serializable {Object field;
private TargetClass(int value) {this.field = value;}
static class PrivateConstructorWrapper {private TargetClass createWithField(int value) {return new TargetClass(value);}}
Step1 process() {return new Step1();}
class Step1 {Step2 m2() {return new Step2();}}
class Step2 {String m3() {return field.toString();}}
void someMethod() {class LocalInner {}}}
package test;
public class OtherClass {static TargetClass.Step1 process(TargetClass target) {return target.process();}}
@interface MyAnnotation {}
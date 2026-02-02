package test;
import otherpackage.OtherClass;
protected abstract class SourceClass {protected TargetClass<?> targetField;
class InnerRecursive {InnerRecursive self;
Object methodToMove() {try {OtherClass oc = new OtherClass();oc.execute(() -> {Object result = targetField::overriddenMethod;return result;});
switch (targetField.superField) {case 1:targetField.variableCall();break;default:break;}return new Object();} catch (Exception e) {return null;}}}}
public abstract class TargetClass<T> extends SuperClass {static class Nested {}
abstract Object overriddenMethod();
synchronized Object overridingMethod() {return new Object();}
void variableCall() {}}
class SuperClass {int superField;}
package otherpackage;
public class OtherClass {void execute(Supplier<Object> supplier) throws Exception {supplier.get();}}
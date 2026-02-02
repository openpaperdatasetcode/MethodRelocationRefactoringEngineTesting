package test;
abstract class TargetParentClass {protected int parentMethod() {return 1;}}
public abstract class TargetClass extends TargetParentClass {Object targetField;static class TargetStaticNested {} // target_feature: static nested class
class TargetInner {} // target_inner}
abstract class SourceParentClass {public abstract Object methodToMove(TargetClass.TargetInner inner);}
abstract class SourceClass extends SourceParentClass {private String outerPrivateField = "private";static class SourceStaticNested {}
public SourceClass() {// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
// Overriding method (method types parameter is:none)@Overrideprivate Object methodToMove(TargetClass.TargetInner inner) {// Variable callTargetClass target = new TargetClass() {};Object var = target.targetField;
// Access outer privateString privateVal = this.outerPrivateField;
// This.methodName(arguments)this.helperMethod();
// InstanceofExpression with numbers:1boolean isTarget = target instanceof TargetClass;int checkResult = isTarget ? 1 : 0;
// With_bounds: generic type with boundsclass BoundedType<T extends TargetClass> {}BoundedType<TargetClass> boundedObj = new BoundedType<>();
// For loop with instance method callfor (int i = 0; i < 1; i++) {int result = new OtherClass().callSuperMethod(target); // 1 as required}
return var + privateVal + checkResult;}
private void helperMethod() {}}
class OtherClass {public int callSuperMethod(TargetClass target) {return target.parentMethod(); // super.methodName()}}
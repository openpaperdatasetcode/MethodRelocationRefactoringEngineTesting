package test;
// Abstract source class (type parameter + anonymous inner class + member inner class)public abstract class SourceClass<T> {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Anonymous inner class (source feature)private final Runnable anonRunnable = new Runnable() {@Overridepublic void run() {new SourceMemberInner().innerMethod();}};
// Parent method for overridingpublic abstract int overridingMethod(AbstractTarget target);}
// Concrete source subclass implementing overridingpublic class SourceSubClass<T extends Number> extends SourceClass<T> {// Overriding method (public access modifier, returns base type)@Overridepublic int overridingMethod(AbstractTarget target) { // per_condition// VariableDeclarationStatement (private, target_feature: obj.field x2, pos: same_package_others)private int field1 = target.targetField1;private int field2 = target.targetField2;
// Switch caseswitch (field1) {case 1:target.targetMethod();break;case 2:AbstractTarget.TargetInner inner = target.new TargetInner();inner.innerMethod();break;}
// Variable calltarget.targetMethod();AbstractTarget.TargetInner targetInner = target.new TargetInner();targetInner.innerMethod();
// Call method (target, public, constructor, super.methodName(arguments), pos: exception throwing statements)try {AbstractTarget.ConcreteTarget concrete = new AbstractTarget.ConcreteTarget(10, 20);concrete.callSuperMethod("test_arg");} catch (IllegalArgumentException e) {throw new RuntimeException("Call failed", e);}
return field1 + field2;}}
// Abstract target class (no features)public abstract class AbstractTarget {public int targetField1;public int targetField2;
public abstract void targetMethod();
// Target inner class (target_inner)public class TargetInner {public void innerMethod() {}}
// Concrete target subclass for constructor invocationpublic static class ConcreteTarget extends AbstractTarget {public ConcreteTarget(int field1, int field2) {super();this.targetField1 = field1;this.targetField2 = field2;}
@Overridepublic void targetMethod() {}
// Call method implementation (super.methodName(arguments))public void callSuperMethod(String arg) {super.toString();if (arg == null) {throw new IllegalArgumentException("Arg cannot be null");}}}}
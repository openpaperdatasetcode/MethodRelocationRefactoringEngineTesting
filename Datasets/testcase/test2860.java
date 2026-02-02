package test;
/**
Javadoc for SourceAbstractClass*/public abstract class SourceAbstractClass {protected int outerProtectedField = 1;
public static class StaticNested {}class MemberInner {}
protected Object methodToMove(TargetAbstractClass... targets) {// Variable call (contains target parameter)if (targets.length > 0) {TargetAbstractClass target = targets[0];target.memberInner.field = 5;}
// Access outer protected fieldint val = outerProtectedField;
// Varargs method in Lambda expressionsRunnable lambda = () -> OthersClass.varargsMethod(1, targets);
return val;}}
/**
Javadoc for TargetAbstractClass (required target_feature)*/public abstract class TargetAbstractClass {MemberInner memberInner = new MemberInner();
class MemberInner {int field;}}
class OthersClass {// Varargs method with specified featuresdefault Object varargsMethod(int num, TargetAbstractClass... targets) {super.toString(); // super.methodName(arguments)return targets;}
// Call method: abstract + this.methodName in functional interfacespublic abstract void callMethod();
public void functionalInterfaceUsage() {Runnable runnable = this::callMethod;runnable.run();}}
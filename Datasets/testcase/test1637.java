package test;
abstract class SuperClass {protected int superField;protected void methodToMove() {}}
private class SourceClass extends SuperClass {@Overrideprotected void methodToMove(TargetClass param) { // contains target parameter (per_condition)// constructor invocationTargetClass instance = new TargetClass();
// variable callinstance.anonymousAction();
// super keywordssuper.superField = 3; // numbers:3, SuperFieldAccess, public modifier (superField is protected but accessed via super)
// while statement with synchronized instance methodint count = 1; // 1while (count > 0) {instance.synchronizedMethod(1); // target, super.methodName(arguments)count--;}
// override_violation (TargetClass has same method signature)param.methodToMove();}}
abstract class TargetClass {// anonymous inner class (target_feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {}};
void anonymousAction() {anonymous.run();}
// synchronized instance methodprotected synchronized void synchronizedMethod(int num) {super.toString(); // super.methodName(arguments)}
// override_violationprotected void methodToMove() {}}
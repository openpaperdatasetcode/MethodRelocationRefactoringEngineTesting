package test.refactor.movemethod;
import java.util.List;import java.util.function.Function;
private interface TestInterface {}
private class SourceClass<T extends Number> implements TestInterface {private class MemberInnerClass {}
/**
Javadoc for varargs method to be refactored
@param targets target class parameters
@return TargetClass instance*/private TargetClass methodToMove(TargetClass... targets) {// Type declaration statementClass<?> stringType = String.class;MemberInnerClass inner = new MemberInnerClass();
// Local inner classclass LocalInnerClass {public void doSomething() {}}LocalInnerClass localInner = new LocalInnerClass();localInner.doSomething();
// Functional interface usage (pos: functional interfaces)Function<TargetClass, List<String>> func = tc -> {// Instance method feature: obj.m1().m2().m3()return tc.getInnerRec().process().collect().toList();};
// Variable callTargetClass firstTarget = targets[0];firstTarget.getAnonymousInner().execute();
return new TargetClass();}}
private class TargetClass {private class TargetInnerRecClass {public TargetInnerRecClass process() { return this; }public TargetInnerRecClass collect() { return this; }public List<String> toList() { return List.of(); }}
// Anonymous inner class (target_feature)private Runnable anonymousInner = new Runnable() {@Overridepublic void run() {}};
public TargetInnerRecClass getInnerRec() {return new TargetInnerRecClass();}
public Runnable getAnonymousInner() {return anonymousInner;}
public void execute() {}}
// Test class for refactoring verificationpublic class MoveMethodTest5049 {public static void main(String[] args) {SourceClass<Integer> source = new SourceClass<>();TargetClass target1 = new TargetClass();TargetClass target2 = new TargetClass();
// Verify original method call (contains target class parameter)TargetClass result = source.methodToMove(target1, target2);
// Verify refactored method call (should be in TargetInnerRecClass)TargetClass.TargetInnerRecClass targetInnerRec = new TargetClass().getInnerRec();TargetClass refactoredResult = targetInnerRec.methodToMove(target1, target2);}}
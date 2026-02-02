package test;
// Source interface (default modifier, same package as target)interface SourceInterface {// Target interface field (satisfies "source contains the field of the target")TargetInterface targetField = new TargetInterface() {};
// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (source feature)class SourceInnerClass {}
// Target method: instance, void return, default access modifierdefault void methodToMove() throws Exception {// Labeled statement (method feature)loopLabel: for (int i = 0; i < 2; i++) {if (i == 1) break loopLabel;}
// Type declaration statement (method feature)class LocalType {}LocalType localObj = new LocalType();
// Super keywords (method feature)Object superObj = super.getClass();
// ReturnStatement with "this.field" and "2" (method feature)if (targetField != null && 2 > 0) {return;}
// Return this; (method feature)variableCall();return;}
// Variable call (method feature)private static void variableCall() {}}
// Target interface (default modifier)interface TargetInterface {// Anonymous inner class (target feature)Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {}};}
// Diff-package class (for "pos": "diff_package_others")package test.other;import test.SourceInterface;import test.TargetInterface;
public class DiffPackageClass {public void useSourceMethod() throws Exception {SourceInterface source = new SourceInterface() {};source.methodToMove();}}
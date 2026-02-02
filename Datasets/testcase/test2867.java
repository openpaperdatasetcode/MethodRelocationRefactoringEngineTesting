package test;
sealed private class SourceClass permits SubClass {protected int outerProtectedField = 3;
class SourceInner {int methodToMove(TargetClass.TargetInnerRec target) throws Exception {// Type declaration statementint localVar;// Constructor invocationTargetClass tc = new TargetClass();// Local inner classclass LocalInner {}LocalInner li = new LocalInner();
// Labeled statementlabel: {// Variable call (source contains target's field)localVar = target.value;break label;}
// DoStatement with target_featuredo {target.this.field = 1;} while (localVar < 1);
// Access outer protectedint val = outerProtectedField;
// Recursion in property assignmentRunnable lambda = () -> recursiveMethod(3);li = (LocalInner) recursiveMethod(3);
return localVar;}
// Recursion methodpublic Object recursiveMethod(int n) throws Exception {if (n <= 0) return new Object();return recursiveMethod(n - 1);}}}
class SubClass extends SourceClass {}
class TargetClass {int field;
class TargetInner {record TargetInnerRec(int value) {TargetClass this$0 = TargetClass.this;}}}
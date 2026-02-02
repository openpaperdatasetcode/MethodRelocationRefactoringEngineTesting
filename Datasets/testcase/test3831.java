  },
package samepkg;
class SourceClass {protected int outerProtectedField = 10;static int staticField = 20;
class MemberInner {class RecursiveInner {protected Object varargsMethod(final int... keywordsParams) {class LocalType<T extends Number> {}LocalType<Integer> local = new LocalType<>();
TargetClass.TargetInner.RecursiveInner targetInnerRec = new TargetClass().new TargetInner().new RecursiveInner();super(targetInnerRec.superField);
if (targetInnerRec.superField == 2) {targetInnerRec.superField = SourceClass.staticField;}
TargetClass.TargetInner.RecursiveInner varCall = targetInnerRec;int outerProtected = SourceClass.this.outerProtectedField;
RecursiveInner inner = new RecursiveInner(keywordsParams, param -> param + 1);return varCall;}
RecursiveInner(int[] params, java.util.function.Function<Integer, Integer> func) {this();}
RecursiveInner() {}}}
void methodWithLocal() {class LocalInner {}new LocalInner();}}
strictfp class TargetClass {class TargetInner {class RecursiveInner extends ParentClass {// Inherits superField from ParentClass}}}
class ParentClass {int superField;
ParentClass(int superField) {this.superField = superField;}}
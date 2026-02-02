package samepkg;
class ParentClass {int superField = 1;
private TargetClass.TargetInner.RecursiveInner callMethod() {TargetClass target = new TargetClass();return this.new TargetClass().new TargetInner().new RecursiveInner();}}
final class SourceClass extends ParentClass {class FirstMemberInner {}class SecondMemberInner {}
strictfp int varargsMethod(TargetClass.TargetInner.RecursiveInner... recursiveInners) {class LocalType<T extends Number> {}LocalType<Integer> localType = new LocalType<>();
synchronized (SourceClass.class) {if (super.superField == 1) {super.superField = 2;}}
TargetClass.TargetInner.RecursiveInner varCall = recursiveInners[0];return varCall.innerField;}}
public class TargetClass {class TargetInner {class RecursiveInner {int innerField;}}}
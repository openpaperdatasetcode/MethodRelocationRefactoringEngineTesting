package test;
sealed class SourceClass permits SourceSubClass1, SourceSubClass2 {protected String protectedField = "protected_data"; // For access_outer_protected
// Member inner class (source feature)public class SourceMemberInner {public void innerInstanceMethod() {}}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class");}};}
// Varargs method (default access modifier, returns TargetClass Type)TargetClass varargsMethod(TargetClass... targetParams) {// Access outer protected fieldString outerProtectedVal = this.protectedField;
TargetClass result = null;for (TargetClass target : targetParams) {result = target;
// Super constructor invocation (target's parent class)TargetInnerRecChild innerChild = target.new TargetInnerRecChild(outerProtectedVal);
// Variable call + access_instance_methodtarget.targetMethod();SourceMemberInner memberInner = new SourceMemberInner();memberInner.innerInstanceMethod();
// Target inner recursive class (target_inner_rec)TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveMethod();}
return result;}}
// Permitted subclasses for sealed SourceClass (source feature: permits)non-sealed class SourceSubClass1 extends SourceClass {}non-sealed class SourceSubClass2 extends SourceClass {}
// Target parent class for super constructor invocationclass TargetParentClass {protected String parentField;
public TargetParentClass(String field) {this.parentField = field;}}
// Target class (final modifier, with local inner class)final class TargetClass extends TargetParentClass {public TargetClass() {super("target_parent_data"); // Super constructor invocation}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveMethod() {}}
// Target inner recursive child class for super constructor invocationpublic class TargetInnerRecChild extends TargetInnerRec {public TargetInnerRecChild(String data) {super(); // Super constructor invocation}}
public void targetMethod() {}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public void localMethod() {}}new TargetLocalInner().localMethod();}}
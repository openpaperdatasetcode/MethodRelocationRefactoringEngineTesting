package test;
sealed enum SourceEnum permits SourceSubEnum {INSTANCE;
// Member inner record (source_inner_rec)protected record SourceInnerRec() {protected int methodToMove(TargetEnum... targets) {super(); // Super constuctor invocationint total = 0;
for (TargetEnum target : targets) {// Variable call + contains target field (per_condition)target.toString();TargetEnum.MemberInner inner = target.new MemberInner();
// Super keywords (access inner class super)String superStr = inner.super.toString();total += superStr.length() + inner.innerField.length();
// Override violation: final method cannot be overriddentry {inner.finalMethod();} catch (UnsupportedOperationException e) {// No new exception}
// Local inner class (source_feature)class LocalCalculator {public int add(int a, int b) {return a + b;}}total += new LocalCalculator().add(5, 3);
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Total: " + total);}};anon.run();}
return total;}}}
non-sealed enum SourceSubEnum extends SourceEnum {}
final enum TargetEnum {TARGET_INSTANCE;
// Member inner class (target_feature)public class MemberInner {public String innerField = "targetInner"; // Source contains target's field (per_condition)
// Final method (causes override_violation if attempted to override)public final void finalMethod() {throw new UnsupportedOperationException("Final method cannot be overridden");}}}
final class OthersClass {// Final others_class static methodpublic static Object callSourceMethod(TargetEnum... targets) {SourceEnum.SourceInnerRec innerRec = SourceEnum.INSTANCE.new SourceInnerRec();// Exception handling statements with instanceReference.methodName(arguments)try {return innerRec.methodToMove(targets);} catch (Exception e) {return null;}}}
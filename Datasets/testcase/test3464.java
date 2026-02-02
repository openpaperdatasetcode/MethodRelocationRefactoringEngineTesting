package sourcepkg;
import targetpkg.TargetRecord;
// Source parent class for extends featureabstract class RecordParent {protected String protectedField = "parent_protected";}
private record SourceRecord(String content) extends RecordParent {// Member inner class (source feature)public class SourceMemberInner {public int overloadMethod() { return 0; }public int overloadMethod(String param) { return param.length(); } // overload_exist}
// Local inner class (source feature)public int createLocalInner() {class SourceLocalInner {public int process(TargetRecord target) {return target.value().length();}}return new SourceLocalInner().process(new TargetRecord("test"));}
// Instance method (public access modifier, returns base type)public int instanceMethod(TargetRecord targetParam) {// Access outer protected fieldString outerProtectedVal = this.protectedField;
// Assert statementassert targetParam != null : "Target parameter cannot be null";
// Labeled statementloopLabel: for (int i = 0; i < 3; i++) {if (i == 1) break loopLabel;}
// Variable call + overload_existtargetParam.process(outerProtectedVal);SourceMemberInner memberInner = new SourceMemberInner();int result1 = memberInner.overloadMethod();int result2 = memberInner.overloadMethod(content);
return result1 + result2;}}
package targetpkg;
// Target parent class for extends featureabstract class TargetRecordParent {protected int parentInt = 10;}
// Target record class (default modifier, extends + anonymous inner class)record TargetRecord(String value) extends TargetRecordParent {public TargetRecord(String value) {super();// Anonymous inner class (target_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anon.run();}
public void process(String data) {}public void process(int data) {} // overload_exist for target}
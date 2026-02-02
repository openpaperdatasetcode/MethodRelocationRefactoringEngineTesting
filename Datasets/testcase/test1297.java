package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum permits SourceEnum.Constant {CONSTANT1, CONSTANT2;
protected String sourceProtectedField = "sourceProtectedValue";private TargetEnum targetField = TargetEnum.TARGET1;
private List<String> sourceMethod() throws IllegalArgumentException {List<String> result = new ArrayList<>();result.add(targetField.name()); // variable callresult.add(this.sourceProtectedField); // access_outer_protectedresult.add(SourceEnum.this.toString()); // uses_outer_this
// Local inner classclass LocalInner {void localMethod() {result.add("localInner");}}new LocalInner().localMethod();
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {result.add("anonymousInner");}};anonymous.run();
if (result.isEmpty()) {throw new IllegalArgumentException("Empty list"); // requires_throws}return result;}
// Local inner enum (conforms to local inner class feature for enum)enum Constant {}}
strictfp enum TargetEnum {TARGET1, TARGET2;
// Member inner classpublic class MemberInner {}
private String callMethod() {super.toString(); // super.methodName(arguments)return TargetEnum.TARGET1.name();}
static {// Call method in static code blocksTargetEnum target = new TargetEnum() {}; // constructor calltarget.callMethod();}
// Override violation: sourceMethod has same signature but different return type contract if moved (List<String> vs default Object)@Overridepublic String toString() {return super.toString();}}
// Test class to trigger refactoring verificationpublic class MoveMethodTest5221 {public static void main(String[] args) throws IllegalArgumentException {SourceEnum.CONSTANT1.sourceMethod();TargetEnum.callMethod();}}
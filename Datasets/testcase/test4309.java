package same.pkg;
import java.util.List;import java.util.ArrayList;
private class SourceClass {private String instanceField = "sourceField";
// Member inner classclass MemberInner {// Overriding method (override_violation demonstration)@Overridepublic String toString() {return "MemberInner";}}
// Local inner class in a methodvoid methodWithLocalClass() {class LocalInner {String getValue() {return instanceField;}}new LocalInner().getValue();}
// Recursive method with target parameter (per condition)public List<String> recursiveMethod(TargetClass targetParam, int depth) {variableCall(targetParam);access_instance_field();
List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
// Enhanced for statementfor (String item : targetParam.items) {result.add(item);}
// Object initialization with source's overriding method callMemberInner inner = new MemberInner() {{SourceClass.staticMethod(this);}};
// Recursive callresult.addAll(recursiveMethod(targetParam, depth - 1));return result;}
// Source's static overriding method (for call_method)public static final void staticMethod(MemberInner inner) {}
private void variableCall(TargetClass param) {String localVar = param.targetField;}
private void access_instance_field() {instanceField = instanceField.toUpperCase();}
// override_violation: Trying to override final methodclass ChildInner extends MemberInner {@Override // Compile error: final method cannot be overriddenpublic String toString() {return "ChildInner";}}}
private class TargetClass {String targetField = "targetValue";List<String> items = new ArrayList<>();
// Target's anonymous inner classvoid methodWithAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {}};runnable.run();}}
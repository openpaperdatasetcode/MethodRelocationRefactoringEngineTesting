package test;
import java.util.ArrayList;import java.util.List;
// Public source record class (anonymous inner class + anonymous inner class)public record SourceRecord(String sourceField) {// First anonymous inner class (source feature)private final Runnable anon1 = new Runnable() {@Overridepublic void run() {method1("anon1");}};
// Second anonymous inner class (source feature)private final Runnable anon2 = new Runnable() {@Overridepublic void run() {method1("anon2");}};
// Instance method (private access modifier, returns List<String>)private List<String> instanceMethod(TargetRecord target) { // per_conditionList<String> result = new ArrayList<>();if (target == null) return result;
// Super constructor invocation (implicit in record, explicit via method call)super.toString();
// Expression statementtarget = new TargetRecord(target.targetField() + "_updated");
// Variable call + access_instance_fieldresult.add(target.targetField());target.targetMethod();
// Ternary operator for instance method featureString param1 = result.isEmpty() ? "default1" : "param1";String param2 = result.size() > 0 ? "param2" : "default2";String param3 = result.size() >= 1 ? "param3" : "default3";
// Instance method feature (3, source, instance, this.methodName(arguments), pos: ternary operators)this.method1(param1);this.method1(param2);this.method1(param3);
// Trigger anonymous inner classesanon1.run();anon2.run();
return result;}
// Helper instance method for featureprivate void method1(String arg) {}}
// Protected target record class (anonymous inner class)protected record TargetRecord(String targetField) {// Anonymous inner class (target_feature)private final Runnable targetAnon = new Runnable() {@Overridepublic void run() {targetMethod();}};
public void targetMethod() {targetAnon.run();}}
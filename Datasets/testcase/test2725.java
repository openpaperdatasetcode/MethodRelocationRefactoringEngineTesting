package test;
public record SourceRecord(String data) {private Object methodToMove(TargetRecord... targets) {Object result = new Object();int index = 0;
// Switch statementswitch (targets.length) {case 0:return "No targets provided";case 1:index = 0;break;default:index = 1;}
for (TargetRecord target : targets) {// Variable call + contains target parameter (per_condition)target.toString();
// ConstructorInvocation with ClassName.field (count 2, pos: source)private TargetRecord.MemberInner inner1 = new TargetRecord.MemberInner(TargetRecord.STATIC_FIELD1);private TargetRecord.MemberInner inner2 = new TargetRecord.MemberInner(TargetRecord.STATIC_FIELD2);
// Depends on static fieldString combined = inner1.getValue() + inner2.getValue() + target.data();
// While loop with inner_class synchronized overloading method callint count = 0;while (count < 1) {String callResult = inner1.process(combined);callResult = inner1.process(combined, "_suffix");result = callResult;count++;}}
return result;}}
private record TargetRecord(String data) {// Static fields for ConstructorInvocation featurepublic static final String STATIC_FIELD1 = "static1_";public static final String STATIC_FIELD2 = "static2_";
// Member inner class (target_feature)public class MemberInner {private String value;
public MemberInner(String prefix) {this.value = prefix + data();}
public String getValue() {return value;}
// Overloading method 1public synchronized String process(String input) {return super.toString() + input;}
// Overloading method 2public synchronized String process(String input, String suffix) {return super.toString() + input + suffix;}}}
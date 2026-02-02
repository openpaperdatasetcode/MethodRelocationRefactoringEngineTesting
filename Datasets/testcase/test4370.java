package same.pkg;
import java.util.ArrayList;import java.util.List;
// Source record: public modifier, with member inner class and anonymous inner classpublic record SourceRecord(String sourceField) {// Member inner class (source_feature)public class SourceMemberInner {private TargetRecord.TargetInner targetInnerField;
public void setTargetInner(TargetRecord.TargetInner inner) {this.targetInnerField = inner;}}
// Instance method: default modifier, returns List<String>List<String> instanceMethod() {// Contains target's field (per_condition)TargetRecord target = new TargetRecord("targetData");TargetRecord.TargetInner targetInner = target.new TargetInner();SourceMemberInner sourceInner = new SourceMemberInner();sourceInner.setTargetInner(targetInner);
variableCall(targetInner);
// 3 public Assignment expressionspublic String assign1 = targetInner.innerField();public List<String> assign2 = new ArrayList<>();public int assign3 = 0;
// NullPointerException checkif (targetInner == null) {throw new NullPointerException("Target inner cannot be null");}
// Super keywordssuper.toString();
// Array initialization with others_class method callList<String>[] lists = new List[]{OthersClass.<String>synchronizedMethod(target, "item1"),OthersClass.<String>synchronizedMethod(target, "item2")};
// Collect resultsList<String> result = new ArrayList<>();result.add(assign1);result.addAll(lists[0]);result.addAll(lists[1]);
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {result.add("anonymous_processed");}};anon.run();
// return this;result.add(this.sourceField());return result;}
private void variableCall(TargetRecord.TargetInner param) {String localVar = param.innerField();}}
// Target record: default modifier, with local inner class (target_feature)record TargetRecord(String targetField) {// Target's inner class (target_inner)public class TargetInner {private String innerField = "innerData";
public String innerField() {return innerField;}
// Method with local inner class (target_feature)public void process() {class TargetLocalInner {String getCombined() {return targetField() + "_" + innerField;}}new TargetLocalInner().getCombined();}}}
// Others class for call_methodclass OthersClass {// Synchronized generic method (call_method)synchronized static <T> List<String> synchronizedMethod(TargetRecord outer, T item) {TargetRecord.TargetInner inner = outer.new TargetInner();List<String> result = new ArrayList<>();result.add(inner.innerField() + "_" + item);return result;}}
package test;
import java.util.List;import java.util.ArrayList;
// Same package others class for DoStatement posclass OthersClass {public void process(TargetRecord target) {}}
private record SourceRecord(String sourceData) {// Source contains target's field (per_condition)private final TargetRecord targetField = new TargetRecord("target_field_data");
// Anonymous inner class (source feature)private final Runnable sourceAnon = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class");}};
// Local inner class (source feature)private void sourceLocalInnerHolder() {class SourceLocalInner {void useTargetField() {System.out.println(targetField.targetData());}}new SourceLocalInner().useTargetField();}
protected List<String> methodToMove() {// type declaration statementclass LocalType {String getValue() { return sourceData + "_local"; }}LocalType localObj = new LocalType();
// variable callsourceAnon.run();sourceLocalInnerHolder();TargetRecord target = targetField;target.targetAnon.run(); // Call target's anonymous inner class
// depends_on_inner_class (depends on source's local inner class)SourceLocalInner inner = new SourceLocalInner();String innerVal = inner.getValue();
// DoStatement (public, this.field, 2, pos:same_package_others)OthersClass others = new OthersClass();int count = 0;do {others.process(target);target = new TargetRecord("updated_" + 2); // this.field (target's component), 2count++;} while (count < 2);
// throw statementif (target.targetData() == null) {throw new IllegalArgumentException("Target field cannot be null");}
// Collect resultsList<String> result = new ArrayList<>();result.add(sourceData);result.add(target.targetData());result.add(innerVal);
return result;}
// Local inner class for depends_on_inner_class (elevated to member scope for accessibility)private class SourceLocalInner {String getValue() { return "source_inner_val"; }}}
record TargetRecord(String targetData) {// target_feature: anonymous inner classfinal Runnable targetAnon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class: " + targetData);}};
// Explicit accessor for target field (record component is implicit, but added for clarity)public String targetData() {return targetData;}}
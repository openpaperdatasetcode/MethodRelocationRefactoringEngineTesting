package test;
private enum SourceEnum implements Runnable {INSTANCE1, INSTANCE2;
private TargetEnum targetField = TargetEnum.VALUE1;private String outerPrivateField = "enum_private_data";
@Overridepublic void run() {// Source inner class (method_position: source_inner)class SourceInner {// Instance method (method type) returning ObjectObject instanceMethod() {// VariableDeclarationStatement (public, target_feature: this.field x3, pos: inner class)public int field1 = this.innerMethod();public String field2 = SourceEnum.this.outerPrivateField;public TargetEnum.TargetInner field3 = SourceEnum.this.targetField.new TargetInner();
// For statement + variable callfor (TargetEnum target : TargetEnum.values()) {target.variableCall();if (target == TargetEnum.VALUE2) {break; // Break statement}}
// Access outer private field + access instance methodString privateVal = SourceEnum.this.outerPrivateField;SourceEnum.this.instanceHelperMethod();
return field3;}
private int innerMethod() {return 100;}}
SourceInner inner = new SourceInner();inner.instanceMethod();}
private void instanceHelperMethod() {}}
// Target enum class (default modifier) with member inner classenum TargetEnum {VALUE1, VALUE2;
private int instanceField = 200;
// Member inner class (target_feature)public class TargetInner {public void doAction() {}}
public void variableCall() {// Access instance methodthis.instanceHelper();}
private void instanceHelper() {}}
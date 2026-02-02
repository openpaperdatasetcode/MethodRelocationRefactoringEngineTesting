package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Non-sealed record source class with required features*/non-sealed record SourceRecord() {// Per_condition: source contains the field of the targetprivate TargetRecord targetField = new TargetRecord(10);
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner classclass SourceMemberInnerClass {}
/**
Instance method to be refactored (protected access, returns List<String>)
@return List<String> result*/protected List<String> refactorTargetMethod() {List<String> result = new ArrayList<>();
// VariableDeclarationStatement in source (type: VariableDeclarationStatement, modifier: private)private int localVar = targetField.value(); // obj.field (target record's component field)private int count = 1; // "1" in target_feature
// Expression statementtargetField.invokeLocalInner();
// 2 ClassInstanceCreation (numbers: "2", modifier: default)SourceStaticNestedClass instance1 = new SourceStaticNestedClass(); // ClassInstanceCreation 1SourceMemberInnerClass instance2 = new SourceMemberInnerClass(); // ClassInstanceCreation 2
// Variable callTargetRecord tempTarget = targetField;result.add(String.valueOf(tempTarget.value()));result.add(String.valueOf(localVar));
// No new exception thrownreturn result;}}
/**
Target record class: default modifier, target_feature: local inner class
*/
record TargetRecord(int value) {
// Target feature: local inner class
public void invokeLocalInner() {
class TargetLocalInnerClass {
void innerMethod() {
System.out.println("Target local inner class: " + value);
}
}
TargetLocalInnerClass localInner = new TargetLocalInnerClass();
localInner.innerMethod();
}
}
// Subclass of non-sealed source record (allowed for non-sealed types)class SubSourceRecord extends SourceRecord {}
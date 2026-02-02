package test.refactoring.movemethod;
/**
Javadoc for TargetRecord (conforms to target_feature: javadoc)
*/
protected record TargetRecord(String targetField) {
// Static nested class (conforms to target_feature)
public static class StaticNestedClass {}
}
private record SourceRecord(TargetRecord targetField, String sourceVar) {protected void sourceMethod() {// Variable call (call targetField's method and sourceVar)targetField.targetField();System.out.println(sourceVar);
// First local inner class (conforms to source feature)class FirstLocalInner {void localMethod() {System.out.println("First Local Inner");}}new FirstLocalInner().localMethod();
// Second local inner class (conforms to duplicate source feature)class SecondLocalInner {void anotherLocalMethod() {System.out.println("Second Local Inner");}}new SecondLocalInner().anotherLocalMethod();}
// Override violation: sourceMethod has same signature as implicit Object methods? No—explicit override conflict if moved@Overridepublic String toString() {return super.toString();}}
// Test class to verify refactoring contextpublic class MoveMethodTest5222 {public static void main(String[] args) {TargetRecord target = new TargetRecord("targetValue");SourceRecord source = new SourceRecord(target, "sourceValue");source.sourceMethod();}}
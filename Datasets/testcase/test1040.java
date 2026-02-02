import java.sql.SQLException;
// Source class with required features: type parameter, two static nested classesclass SourceClass<T> {// Field referencing target class to satisfy per_conditionTargetClass targetField;
// Static nested class 1static class StaticNestedClass1 {private int value;}
// Static nested class 2static class StaticNestedClass2 {private String text;}
/**
Javadoc for the test method to be moved
@throws SQLException required throws clause*/@Deprecated // Annotation to satisfy has_annotationprivate void methodToMove() throws SQLException {// Type declaration statementString localVar = "test";
// Variable calltargetField.innerClassInstance.printValue();
// EnhancedForStatement in inner context (via super.field access)for (Integer num : targetField.innerClassInstance.getNumbers()) {// Super.field access (inner class accesses super field)if (num == targetField.innerClassInstance.superField) {throw new SQLException("Test exception");}}
// Override violation (method with same signature as super but incompatible throws)// This method declares SQLException while super may not, causing override violation}}
// Target class with member inner classclass TargetClass {// Member inner class (target_feature)class InnerClass {// Super.field for EnhancedForStatement target_featureint superField = 1;
private Integer[] getNumbers() {return new Integer[]{1, 2, 3};}
private void printValue() {System.out.println(superField);}}
// Instance of inner class for variable callInnerClass innerClassInstance = new InnerClass();
// Method with same signature to cause override violation if moved (no throws SQLException)private void methodToMove() {// Empty to create override violation when moving method with throws}}

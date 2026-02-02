package test.refactoring.movemethod;
import java.util.List;
protected record SourceRecord() {// Instance method to be refactored: final access, returns TargetClass typepublic final TargetClass targetBoundMethod(TargetClass targetParam) {// Empty statement;
// Type declaration statementclass LocalType {}LocalType localInstance = new LocalType();
// Expression statementtargetParam.toString();
// For statementfor (int i = 0; i < 5; i++) {System.out.println(i);}
// Variable callTargetClass tempTarget = targetParam;
// Access instance field (target class instance field)int targetFieldValue = tempTarget.field();
// With bounds (generic type with bounds)List<? extends CharSequence> boundedList = List.of("bounds");
// No new exception thrownreturn tempTarget;}}
// Target class: record class, default modifier, contains member inner classrecord TargetClass(int field) {// Member inner class (target_feature)class TargetMemberInnerClass {}}
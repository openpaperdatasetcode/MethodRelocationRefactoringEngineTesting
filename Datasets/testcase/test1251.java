package test.pkg;
private class SourceClass {private TargetClass targetInstance = new TargetClass(); // Source contains target field (per_condition)
/**
Varargs method to test Move Method refactoring, demonstrating constructor invocation,
continue statement, and variable call features.
@param values variable arguments for processing
@return count of valid processed values (base type: int)*/public int processValues(String... values) {int count = 0;TargetClass.MemberInnerClass targetInner = targetInstance.new MemberInnerClass(); // Variable call: target's member inner class
for (String value : values) {// Expression statementboolean isValid = value != null && !value.isBlank();
if (!isValid) {continue; // Continue statement}
// Constructor invocation (target's member inner class)TargetClass.MemberInnerClass newInnerInstance = targetInstance.new MemberInnerClass();newInnerInstance.process(value); // Variable call: inner class method
count++;}
return count; // Base type return (int)}}
private class TargetClass {// Member inner class (target_feature)public class MemberInnerClass {public void process(String data) {// Inner class method implementation}}
// Method will be moved here:// /**// * Varargs method to test Move Method refactoring, demonstrating constructor invocation,// * continue statement, and variable call features.// * @param values variable arguments for processing// * @return count of valid processed values (base type: int)// */// public int processValues(String... values) { ... }}
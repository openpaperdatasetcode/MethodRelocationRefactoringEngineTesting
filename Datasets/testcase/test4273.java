package test;
class SourceClass {private int outerPrivateField = 10;private TargetClass targetField = new TargetClass();
// First local inner class (source_class feature)void createFirstLocalInner() {class FirstLocalInner {void callRecursive() {recursiveMethod(3);}}new FirstLocalInner().callRecursive();}
// Second local inner class (source_class feature)void createSecondLocalInner() {class SecondLocalInner {void useTargetMethods() {// Call 2 target instance methods (matches method_feature "2")int val1 = TargetClass.getStaticField();int val2 = targetField.getInnerFieldValue();}}new SecondLocalInner().useTargetMethods();}
// Recursive method to be refactoredprotected void recursiveMethod(int depth) {if (depth <= 0) return;
// Assert statementassert targetField != null : "Target field cannot be null";
// Enhanced for statementint[] nums = {1, 2, 3};for (int num : nums) {// Variable call + access outer privatetargetField.innerField = outerPrivateField + num;
// Switch caseswitch (depth) {case 1:targetField.innerClass.doAction("case1");break;case 2:targetField.innerClass.doAction("case2");break;default:targetField.innerClass.doAction("default");}}
// RecursionrecursiveMethod(depth - 1);}}
class TargetClass {int innerField;// Member inner class (target_class feature)TargetInner innerClass = new TargetInner();private static int staticField = 20;
// 1st target instance method (for method_feature)public int getInnerFieldValue() {return innerField;}
// 2nd target instance method (for method_feature)public static int getStaticField() {return staticField;}
class TargetInner {void doAction(String action) {}}}
package test;
// Source enum: default modifier, same package with target, has static nested + local inner classesenum SourceEnum {// Enum constantsCONST_ONE, CONST_TWO;
// Static nested class (matches source_class.feature)static class SourceStaticNested {String nestedField;}
// Recursive method to refactor (matches method.type: recursion)Object moveMethod(TargetEnum targetParam, int depth) {// Base case for recursionif (depth <= 0) {return targetParam;}
// Super keywords (matches method.features)String superStr = super.toString();
// Variable call: access target's field (matches per_condition)int targetFieldVal = targetParam.targetField;// Access target's member inner class (matches target class: target)TargetEnum.TargetInner targetInner = targetParam.new TargetInner();
// Method with local inner class (matches source_class.feature)class SourceLocalInner {// TypeDeclarationStatement in inner class (matches nested feature)private void declareType(TargetEnum target) {// obj.field access + "1" (target_feature), private modifierif (target.targetField == 1) {targetInner.setInnerField(targetFieldVal);}}}new SourceLocalInner().declareType(targetParam);
// overload_exist: call overloaded methodString overloadResult1 = processData(targetFieldVal);String overloadResult2 = processData(targetFieldVal, superStr);
// Recursive call (matches method.type: recursion)return moveMethod(targetParam, depth - 1);}
// Overloaded method 1 (for overload_exist)private String processData(int val) {return String.valueOf(val);}
// Overloaded method 2 (for overload_exist)private String processData(int val, String suffix) {return val + "_" + suffix;}}
// Target enum: strictfp modifier, has member inner class (target_feature)strictfp enum TargetEnum {// Enum constantsTARGET_CONST_ONE(1), TARGET_CONST_TWO(2);
// Target field (accessed by source method, matches per_condition)final int targetField;
// Target enum constructorTargetEnum(int targetField) {this.targetField = targetField;}
// Member inner class (matches target_class.target_feature)class TargetInner {private int innerField;
void setInnerField(int val) {this.innerField = val;}
int getInnerField() {return innerField;}}}
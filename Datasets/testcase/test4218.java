package test;
public class SourceClass {// Member inner class (matches source_class.feature)class SourceMemberInner {int innerField;
SourceMemberInner(int val) {this.innerField = val;}}
// Method with local inner class (matches source_class.feature)public void methodWithLocalInner() {class SourceLocalInner {void printData(String data) {System.out.println(data);}}new SourceLocalInner().printData("Local Inner Class");}
/**
Javadoc for the refactored method (matches method.features: method javadoc)
@param targetParam Target class instance (matches per_condition)
@return base type (int) result*/public int moveMethod(TargetClass targetParam) {// Constructor invocation: create source member inner class instanceSourceMemberInner innerInst = new SourceMemberInner(5);
// ClassInstanceCreation with numbers:1 (1 instance creation, private modifier logic)// Private modifier reflected by using inner class instance (only accessible in SourceClass)int innerVal = innerInst.innerField;
// Variable call: access target's field (matches per_condition)int targetFieldVal = targetParam.targetField;
// with_bounds: use generic with bounds (via target's generic method)Integer boundedResult = targetParam.processBoundedData(innerVal + targetFieldVal);
// depends_on_inner_class: use source inner class methodinnerInst.innerField = boundedResult;
// Return base type (int)return innerInst.innerField;}}
public class TargetClass {// Target field (accessed by source method, matches per_condition)int targetField = 10;
// Method with local inner class (matches target_class.target_feature)public void methodWithTargetLocalInner() {class TargetLocalInner {<T extends Number> T process(T num) {return num;}}new TargetLocalInner().process(15);}
// with_bounds: generic method with upper bound (Number)public <T extends Number> T processBoundedData(T data) {return data;}}

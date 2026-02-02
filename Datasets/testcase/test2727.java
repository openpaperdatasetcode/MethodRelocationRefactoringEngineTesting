package test;
interface TestInterface {}
class SourceClass implements TestInterface {// Member inner class (source_feature)public class SourceMemberInner {public void processTarget(TargetClass<String> target) {target.toString();}}
// Accessor method (setter)public void setTargetField(TargetClass<String> target, String value) {// Variable call + contains target parameter (per_condition)target.toString();TargetClass<String>.TargetInner inner = target.new TargetInner();
// Local inner class (source_feature)class SourceLocalInner {public void validate(String val) {if (val != null) inner.setInnerField(val);}}
new SourceLocalInner().validate(value);
// For loop with parent_class static method callfor (int i = 0; i < 1; i++) {ParentTargetClass.staticProcess(target.new TargetInner());}
try {// Access target inner class accessorString field = inner.getInnerField();} catch (Exception e) {// No new exception}}}
public class TargetClass<T extends CharSequence> extends ParentTargetClass {// Type parameter (target_feature)public class TargetInner {private T innerField; // Source contains target's field (per_condition)
// Accessor methodspublic T getInnerField() {return innerField;}
public void setInnerField(T innerField) {this.innerField = innerField;}}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}
class ParentTargetClass {// Parent class static method (method_feature)protected static void staticProcess(TargetClass<?>.TargetInner inner) {// outerInstance.new InnerClass().methodName() implied in callif (inner != null) inner.getInnerField();}}
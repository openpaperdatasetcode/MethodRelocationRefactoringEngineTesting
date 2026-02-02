package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
class ParentSourceClass {protected String superField = "parentSuperField"; // super.field for target_feature}
// Source class: public, same package, 2 static nested classespublic class SourceClass extends ParentSourceClass {// Target class field (per_condition: source contains target field)private TargetClass<String> targetField = new TargetClass<>();
// First static nested class (source_feature)public static class FirstSourceNested {}
// Second static nested class (source_feature) - method_position: source_innerpublic static class SecondSourceNested {/**
Accessor method to be refactored (type: accessor)*/void setTargetProperties() {// EmptyStatement (type: EmptyStatement, modifier: private, target_feature: super.field, 2, pos: source)private void emptyStmtExample() {if (SourceClass.super.superField != null && targetField != null) {}; // EmptyStatement; super.field + 2 conditions}
// Abstract method feature (type: abstract, modifier: default, pos: property assignment)abstract class AbstractHelper {default void assignProperties() {// OuterClass.InnerClass.methodName()SourceClass.SecondSourceNested.setDefaultValue(targetField);}}
// 3 Assignment expressions (numbers: "3", modifier: default, exp: "Assignment")String prop1 = targetField.getProperty1();prop1 = "updated1";targetField.setProperty1(prop1); // 1st assignmenttargetField.setProperty2(100); // 2nd assignmenttargetField.setProperty3(new ArrayList()); // 3rd assignment (raw_type)
// Variable call + access_instance_methodAbstractHelper helper = new AbstractHelper() {};helper.assignProperties();emptyStmtExample();
// Throw statementif (targetField.getProperty1() == null) {throw new IllegalArgumentException("Property1 cannot be null");}}
// OuterClass.InnerClass.methodName() supportprivate static void setDefaultValue(TargetClass<?> target) {target.setProperty1("default");}}}
// Target class: default modifier, target_feature: type parameter + local inner classclass TargetClass<T> {private String property1;private int property2;private List property3; // raw_type
// Target_inner: target class for method movementpublic class TargetInnerClass {// Refactored accessor methodvoid setTargetProperties() {// EmptyStatement (consistent with source)private void emptyStmtExample() {if (SourceClass.super.superField != null && TargetClass.this != null) {};}
// Abstract method featureabstract class AbstractHelper {default void assignProperties() {SourceClass.SecondSourceNested.setDefaultValue(TargetClass.this);}}
// 3 Assignment expressionsString prop1 = getProperty1();prop1 = "updated1";setProperty1(prop1);setProperty2(100);setProperty3(new ArrayList());
// Variable call + access_instance_methodAbstractHelper helper = new AbstractHelper() {};helper.assignProperties();emptyStmtExample();
// Throw statementif (getProperty1() == null) {throw new IllegalArgumentException("Property1 cannot be null");}}}
// Local inner class (target_feature)public void useLocalInner() {class LocalInnerClass {public void process() {TargetInnerClass inner = new TargetInnerClass();inner.setTargetProperties();}}new LocalInnerClass().process();}
// Accessor methods (supports method type: accessor)public String getProperty1() { return property1; }public void setProperty1(String property1) { this.property1 = property1; }public int getProperty2() { return property2; }public void setProperty2(int property2) { this.property2 = property2; }public List getProperty3() { return property3; }public void setProperty3(List property3) { this.property3 = property3; }}
// Test class for refactoring verificationpublic class MoveMethodTest5055 {public static void main(String[] args) {SourceClass source = new SourceClass();SourceClass.SecondSourceNested sourceInner = new SourceClass.SecondSourceNested();
// Original method call (before refactoring)sourceInner.setTargetProperties();
// Refactored method call (after moving to TargetInnerClass)TargetClass<String> target = new TargetClass<>();TargetClass.TargetInnerClass targetInner = target.new TargetInnerClass();targetInner.setTargetProperties();
// Verify target_feature: local inner class usagetarget.useLocalInner();}}
package test;
import java.util.List;import java.util.ArrayList;
// Source class: strictfp modifier, generic (type parameter), has static nested + local inner classesstrictfp class SourceClass<T> {// Static nested class (matches source_class.feature)static class SourceStaticNested {static int staticNestedField;}
// Instance field for access_instance_fieldprivate String sourceInstanceField = "sourceInstanceData";
// Overloading method 1 (matches method.type: overloading)List<String> moveMethod(TargetClass targetParam) {return moveMethod(targetParam, 1);}
// Overloading method 2 (refactored, matches method.type: overloading)List<String> moveMethod(TargetClass targetParam, int num) {// Super constructor invocation (matches method.features)super();
List<String> result = new ArrayList<>();// Access target's field (matches per_condition: source contains target's field)String targetFieldVal = targetParam.targetField;result.add(targetFieldVal);
// try statement (matches method.features)try {// access_instance_field: access source's instance fieldresult.add(sourceInstanceField);// Variable call: use target's field in calculationresult.add(targetFieldVal + "_" + num);} catch (Exception e) {// no_new_exception: no additional exception thrown}
// Local inner class (matches source_class.feature)class SourceLocalInner {void localMethod() {result.add("localInnerData");}}new SourceLocalInner().localMethod();
return result;}
// Constructor with static method in parameter list (matches nested static method's pos)SourceClass() {this(SourceClass.getStaticBaseType(1));}
private SourceClass(int dummy) {}
// Nested static method: default modifier, return base type, pos: constructor parameter liststatic int getStaticBaseType(int arg) {return arg * SourceStaticNested.staticNestedField;}}
// Target class: final modifier, no extra features (matches target_class specs)final class TargetClass {// Target field (accessed by source method, matches per_condition)String targetField = "targetDefaultData";}
package test.refactoring.movemethod;
import java.lang.reflect.Method;
// Marker annotation for method (has_annotation)@interface RefactorMarker {}
// Interface for target class implementation (target_feature: implements)interface TargetInterface {void doAction();}
// Source normal class (default modifier, same package)class SourceClass {// Target class field (per_condition: source contains target's field)private TargetClass targetField = new TargetClass();
// Member inner class (method_position: source_inner)class SourceInnerClass {// Final instance method to be refactored (method.type: instance, access_modifier: final)@RefactorMarker // has_annotation (method.features)final Object refactorMethod() { // method types parameter is:none// Variable call (method.features)String varCall = targetField.getStaticNested().getName();Object result = varCall + "_processed";
// Used by reflection (method.features)try {Method reflectMethod = SourceInnerClass.class.getMethod("refactorMethod");result = reflectMethod.invoke(this);} catch (Exception e) {// no_new_exception (method.features)}
return result;}}
// Call method (type: source, modifier: protected, return_type: int)protected int callInIfElse(boolean flag) {SourceInnerClass inner = new SourceClass().new SourceInnerClass();// pos: if/else conditions (call_method.pos)if (flag) {// target_feature: static + outerInstance.new InnerClass().methodName()return TargetClass.StaticNested.staticMethod(inner.refactorMethod().hashCode());} else {return TargetClass.StaticNested.staticMethod(0);}}}
// Target normal class (default modifier, same package)class TargetClass implements TargetInterface { // target_feature: implements// Static nested class (target_feature: static nested class)static class StaticNested {private String name = "StaticNested";
// Static method for call_method target_featurepublic static int staticMethod(int param) {return param * 2;}
public String getName() {return name;}}
// Implement interface method@Overridepublic void doAction() {}
// Getter for static nested class instancepublic StaticNested getStaticNested() {return new StaticNested();}}

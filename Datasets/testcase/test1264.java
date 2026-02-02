package test.refactoring.movemethod;
import java.io.IOException;
// Parent class for source_class (feature: extends)class ParentSourceClass {protected String parentField = "parent_data";}
// Source normal class (private modifier, same package, extends ParentSourceClass)private class SourceClass extends ParentSourceClass {// Private instance method to be refactored (method.type: instance, access_modifier: private)private void refactorMethod(TargetClass targetParam) throws IOException { // contains target parameter (per_condition), requires_throwssuper(); // super constructor invocation (method.features)
// Variable call (method.features)String targetNestedValue = targetParam.StaticNested.getStaticField();String parentValue = super.parentField;String combined = targetNestedValue + parentValue;
// Assert statement (method.features)assert combined.length() > 0 : "Combined value cannot be empty";
// Use target parameter (per_condition validation)targetParam.process(combined);}}
// Target normal class (default modifier, same package)class TargetClass {// Static nested class (target_feature)static class StaticNested {public static String getStaticField() {return "target_static_data";}}
// Helper method for method logicpublic void process(String data) {// Do nothing for test case}}
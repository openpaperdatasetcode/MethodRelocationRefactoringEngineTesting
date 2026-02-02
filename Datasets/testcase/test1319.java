package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
// Parent class for constructor feature and super keywordsclass ParentClass {protected String parentField = "ParentData";
public ParentClass(String param) {}public ParentClass(String param1, String param2) {} // Overloaded constructor (2 occurrences)}
// Others_class for call_method (final modifier, accessor)final class OtherAccessorClass {private String data;
public OtherAccessorClass(String data) {this.data = data;}
// Accessor method (call_method target_feature)public String getData() {return data;}}
// Source class (public, same package, no features)public class SourceClass {// Method to be refactored: instance, final, List<String> returnpublic final List<String> processTarget(TargetClass targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;List<String> result = new ArrayList<>();
// Super keywordsParentClass parent = new ParentClass(targetField);result.add(super.toString());result.add(parent.parentField);
// For statement with constructor feature (parent_class, constructor, instanceReference::methodName)Supplier<ParentClass> constructorRef1 = () -> new ParentClass(targetField, "param2");Supplier<ParentClass> constructorRef2 = ParentClass::new; // Overloaded constructor (2nd occurrence)for (int i = 0; i < 2; i++) {ParentClass constructed = (i == 0) ? constructorRef1.get() : constructorRef2.get("param1");result.add(constructed.parentField + "_" + i);}
// Variable call + access_instance_method (target's member inner class)TargetClass.TargetInner targetInner = targetParam.new TargetInner();result.add(targetInner.getInnerData(targetField));
// Switch statement with call_method (instanceReference::methodName)OtherAccessorClass accessor = new OtherAccessorClass(targetField);String switchInput = targetParam.getTargetType();switch (switchInput) {case "TYPE1":result.add(accessor::getData); // Method referencebreak;case "TYPE2":result.add(accessor.getData()); // Direct accessor callbreak;default:; // Empty statement}
try {// Access_instance_method: target's inner class methodresult.add(targetInner.process(targetField));// Constructor feature (2nd occurrence: overloaded constructor)ParentClass overloadedParent = new ParentClass(targetField, "OverloadParam");result.add(overloadedParent.parentField);} catch (IllegalArgumentException e) {// no_new_exception: rethrow without wrappingthrow e;}
return result;}}
// Target class (protected, target_feature: member inner class)protected class TargetClass {public String targetField = "TargetData";
// Target_feature: member inner classpublic class TargetInner {public String getInnerData(String input) {return "Inner:" + input;}
public String process(String input) {return "ProcessedInner:" + input;}}
public String getTargetType() {return "TYPE1";}}
// Test classpublic class MoveMethodTest5256 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();List<String> result = source.processTarget(target);System.out.println("Refactoring test result: " + result);}}
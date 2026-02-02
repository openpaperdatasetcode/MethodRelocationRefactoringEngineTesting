import java.util.ArrayList;import java.util.List;
// TargetClass: normal, package-private, with static nested class (meets target_class specs)class TargetClass {// Fields for "obj.field" access (2 fields)public String field1;public String field2;
// Static nested class (target_feature)public static class TargetStaticNested {public static String staticField = "STATIC_NESTED_DATA";
public static String combine(String s1, String s2) {return s1 + "_" + s2;}}
public TargetClass(String f1, String f2) {this.field1 = f1;this.field2 = f2;}}
// SourceClass: normal, private, same package as target, with 2 local inner classes (meets source_class specs)private class SourceClass {// Source contains target field (per_condition)private TargetClass sourceTargetField;private List<String> resultList;
// Constructor: meets method specs (constructor type, List<String> return via field, default access, source position)SourceClass(TargetClass target) {this.sourceTargetField = target;this.resultList = new ArrayList<>();
// ForStatement: static modifier effect, pos=source, access 2 obj.fieldfor (int i = 0; i < 2; i++) {// Access target's 2 fieldsString combined = TargetClass.TargetStaticNested.combine(sourceTargetField.field1 + "" + i,
sourceTargetField.field2 + "" + i);resultList.add(combined);
// Break statement (method_feature)if (i == 1) {break;}}
// Do statement (method_feature)int count = 0;do {// Variable call: invoke static nested class methodString staticData = TargetClass.TargetStaticNested.staticField + "_" + count;resultList.add(staticData);count++;} while (count < 2);
// 1st local inner class (source_feature)class LocalProcessor1 {void process() {resultList.add("processed_by_1:" + sourceTargetField.field1);}}new LocalProcessor1().process();
// 2nd local inner class (source_feature)class LocalProcessor2 {void process() {resultList.add("processed_by_2:" + sourceTargetField.field2);}}new LocalProcessor2().process();}
// Method to return result (simulate constructor "return_type: List<String>")public List<String> getResult() {return resultList;}}
// Test entrypublic class SourceTest {public static void main(String[] args) {// Create target instance with fieldsTargetClass target = new TargetClass("initial1", "initial2");// Create source instance (contains target field: per_condition)SourceClass source = new SourceClass(target);
// Verify result (no_new_exception)List<String> result = source.getResult();System.out.println("Processing result: " + result);}}
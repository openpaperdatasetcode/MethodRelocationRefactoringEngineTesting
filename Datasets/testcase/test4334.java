package same.pkg;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
// Source record: sealed modifier, with permits, static nested class, anonymous inner classsealed record SourceRecord(String sourceField) permits ExtendedSourceRecord {// Source's static nested class (matches source_class feature)public static class SourceStaticNested {public String process(String input) {return input.toUpperCase();}}
// Instance method: final modifier, returns List<String>, contains target parameter (per_condition)public final List<String> instanceMethod(TargetRecord targetParam) {variableCall(targetParam);access_instance_method(targetParam);
List<String> result = new ArrayList<>();TypeDeclaration typeDecl = new TypeDeclaration(); // Type declaration statement
// Enhanced for statement (iterates target's static nested class list)for (TargetRecord.TargetStaticNested nestedItem : targetParam.nestedItems()) {result.add(nestedItem.nestedField());}
// Do statement (adds source field until size reaches 3)int doCount = 0;do {result.add(SourceRecord.this.sourceField()); // uses_outer_thisdoCount++;} while (doCount < 3);
// Switch case (handles target's static nested class field length)for (TargetRecord.TargetStaticNested nested : targetParam.nestedItems()) {switch (nested.nestedField().length()) {case 3 -> result.add("Len3: " + nested.nestedField());case 4 -> result.add("Len4: " + nested.nestedField());default -> result.add("OtherLen: " + nested.nestedField());}}
// used_by_reflection: invoke target's instance methodtry {Method targetMethod = TargetRecord.class.getMethod("getTargetField");String reflectedVal = (String) targetMethod.invoke(targetParam);result.add("Reflected: " + reflectedVal);} catch (Exception e) {// no_new_exception: no additional checked exceptions thrown}
// Anonymous inner class (source_class feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceRecord method");}};anonRunnable.run();
return result;}
// Variable call (uses target's field)private void variableCall(TargetRecord param) {String localVar = param.targetField();}
// Access target's instance methodprivate void access_instance_method(TargetRecord param) {param.validate();}
// Nested type declaration (matches method.feature)private static class TypeDeclaration {}}
// Extended record for source's permits clausenon-sealed record ExtendedSourceRecord(String sourceField, int extraInt) extends SourceRecord {public ExtendedSourceRecord(String sourceField, int extraInt) {super(sourceField);}}
// Target record: default modifier, with static nested class (target_feature)record TargetRecord(String targetField, List<TargetStaticNested> nestedItems) {// Target's static nested class (matches target_class feature)public static record TargetStaticNested(String nestedField) {}
// Target's instance method (for access_instance_method)public void validate() {if (targetField == null || targetField.isBlank()) {throw new IllegalArgumentException("Target field cannot be blank");}}
// Getter for reflection callpublic String getTargetField() {return targetField;}}
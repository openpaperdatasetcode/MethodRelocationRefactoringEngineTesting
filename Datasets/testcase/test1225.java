package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
// Super class for source_class super keywords and call_method super.methodName()class SuperSourceClass {protected static String superStaticField = "SuperStatic-Data";
protected void log(String message) {System.out.println("SuperSourceClass: " + message);}}
// Others_class for call_method (private static method + super.methodName())class OthersClass extends SuperSourceClass {// Call_method: private static method, super.methodName(arguments)private static void processStaticData(String input) {super.log("Processing others data: " + input); // Super method invocation}}
// Source class: public normal class, local inner class + static nested class, extends SuperSourceClasspublic class SourceClass extends SuperSourceClass {// Static field for depends_on_static_field featurepublic static String sourceStaticField = "SourceStatic-Value";
// Static nested class (source_class feature)public static class SourceStaticNested {public static String format(String input) {return input + "-" + sourceStaticField + "-" + superStaticField;}}
// Method to be refactored: private instance method, returns List<String>private List<String> generateStrings(TargetClass targetParam, String... prefixes) {List<String> result = new ArrayList<>();int index = 0;
// 3 CreationReference expressions (public modifier)Supplier<String> ref1 = String::new; // 1st CreationReference (String constructor reference)Supplier<TargetClass.TargetStaticNested> ref2 = TargetClass.TargetStaticNested::new; // 2nd (target nested class constructor reference)Supplier<SourceStaticNested> ref3 = SourceStaticNested::new; // 3rd (source nested class constructor reference)
// While statementwhile (index < prefixes.length) {String prefix = prefixes[index];if (prefix == null || prefix.isBlank()) {index++;continue; // Continue statement}
// Super keywords: access super class's static field and methodString superData = super.superStaticField;super.log("Processing prefix: " + prefix);
// Variable call: targetParam, static nested classes, CreationReference instancesTargetClass.TargetStaticNested targetNested = ref2.get();SourceStaticNested sourceNested = ref3.get();String formatted = sourceNested.format(prefix);String enhanced = targetNested.enhance(formatted, superData);
// Ternary operators (pos for call_method)String finalData = enhanced.length() > 20 ? enhanced.substring(0, 20) : enhanced;OthersClass.processStaticData(finalData); // Call_method: others_class static method
result.add(finalData);
// Depends_on_static_field: rely on source, target, and super static fieldsif (sourceStaticField.isEmpty() || TargetClass.targetStaticField.isEmpty() || superStaticField.isEmpty()) {result.add("Static field empty warning");}
index++;}
// Local inner class (source_class feature)class LocalStringValidator {public boolean isValid(String s) {return s != null && !s.isEmpty();}}
LocalStringValidator validator = new LocalStringValidator();result.removeIf(s -> !validator.isValid(s));
return result;}
// Trigger method for testingpublic List<String> executeGeneration(TargetClass targetParam, String... prefixes) {return generateStrings(targetParam, prefixes);}}
// Target class: public normal class, static nested class (target_feature)public class TargetClass {// Static field for depends_on_static_field featurepublic static String targetStaticField = "TargetStatic-Data";
// Target_feature: static nested classpublic static class TargetStaticNested {public String enhance(String input, String superData) {return input + "-enhanced-" + superData + "-" + targetStaticField;}}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5449Test {@Testvoid testOriginalMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();String[] testPrefixes = {"apple", "", "banana", null, "cherry"};
List<String> result = source.executeGeneration(target, testPrefixes);
// Verify valid prefixes processing (skips blank/null)assertEquals(3, result.size());assertTrue(result.containsAll(List.of("apple-SourceStatic-Value-Su", // Truncated (length >20)"banana-SourceStatic-Value-S", // Truncated"cherry-SourceStatic-Value-S" // Truncated)));}
@Testvoid testStaticFieldDependency() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Modify static fields to trigger warningSourceClass.sourceStaticField = "";String[] testPrefixes = {"test"};
List<String> result = source.executeGeneration(target, testPrefixes);assertEquals(2, result.size()); // 1 processed string + 1 warningassertTrue(result.contains("Static field empty warning"));
// Restore static fieldsSourceClass.sourceStaticField = "SourceStatic-Value";TargetClass.targetStaticField = "TargetStatic-Data";SuperSourceClass.superStaticField = "SuperStatic-Data";}
@Testvoid testRefactoredMethod() {SourceClass source = new SourceClass();TargetClass target = new TargetClass();String[] testPrefixes = {"ref1", "ref2"};
// After refactoring: method moved to TargetClasstry {java.lang.reflect.Method refactoredMethod = TargetClass.class.getDeclaredMethod("generateStrings", SourceClass.class, String[].class);refactoredMethod.setAccessible(true);List<String> refactoredResult = (List<String>) refactoredMethod.invoke(target, source, (Object) testPrefixes);
assertEquals(2, refactoredResult.size());assertTrue(refactoredResult.stream().allMatch(s -> s.startsWith("ref")));} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testCreationReferences() {// Verify CreationReference expressions are validSupplier<String> ref1 = String::new;Supplier<TargetClass.TargetStaticNested> ref2 = TargetClass.TargetStaticNested::new;Supplier<SourceClass.SourceStaticNested> ref3 = SourceClass.SourceStaticNested::new;
assertNotNull(ref1.get());assertNotNull(ref2.get());assertNotNull(ref3.get());}}
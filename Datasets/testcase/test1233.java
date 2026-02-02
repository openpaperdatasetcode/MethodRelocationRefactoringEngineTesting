package test.refactoring.movemethod;
import test.refactoring.other.DiffPackageSuperClass;import java.util.ArrayList;import java.util.List;import java.util.function.Function;
// Parent class for target_class extends and call_method parent_classclass GenericParentClass<T> {// Call_method: private static method, ClassName::methodName (method reference)private static Object mapData(U data, Function<U, Object> mapper) {
return mapper.apply(data);
}
}
// Source class: default generic class, anonymous inner class + member inner classclass SourceClass<T extends CharSequence> {// Source contains target field (per_condition)private final TargetClass<T> targetField = new TargetClass<>();
// Member inner class (source_class feature)public class SourceInner {// Overload_exist feature: overloaded methodspublic void process(T data) {}public void process(T data, int priority) {}}
// Method to be refactored: strictfp instance method, returns void, requires_try_catchstrictfp void populateTarget(T... items) {SourceInner inner = new SourceInner();List<T> itemList = new ArrayList<>();
// Type declaration statementDiffPackageSuperClass diffPackageObj = new DiffPackageSuperClass();TargetClass<T>.TargetInner targetInner = targetField.new TargetInner();
// ConstructorInvocation (public modifier, target_feature: ClassName.field + 1, pos: diff_package_others)OtherClass otherObj1 = new OtherClass(DiffPackageSuperClass.STATIC_FIELD); // ClassName.field (diff package static field)OtherClass otherObj2 = new OtherClass(DiffPackageSuperClass.STATIC_FIELD + "_1"); // 1 (target_feature: suffix with 1)
// Super constructor invocation (via target inner class)targetInner.initialize();
// Requires_try_catchtry {// Variable call: targetField, inner class, overloaded methodsfor (T item : items) {if (item != null) {itemList.add(item);inner.process(item); // Overload 1inner.process(item, item.length()); // Overload 2 (overload_exist)}}
// Collection operations (pos for call_method: ClassName::methodName)List<Object> mappedData = new ArrayList<>();for (T item : itemList) {// Call_method: parent_class private static method, method referenceObject result = GenericParentClass.mapData(item, CharSequence::length);mappedData.add(result);}targetField.setMappedData(mappedData);
// otherObject.process(this); feature: pass source instance to other object's methoddiffPackageObj.process(this);
// Anonymous inner class (source_class feature)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous task: Processed " + itemList.size() + " items");}};anonymousTask.run();
} catch (IllegalArgumentException e) {System.err.println("Population failed: " + e.getMessage());}}
// Getter for target field to verify refactoringpublic TargetClass<T> getTargetField() {return targetField;}
// Helper method for otherObject.process(this)public String getSourceData() {return "Source-" + targetField.getMappedData().size();}}
// Target class: public generic class, extends GenericParentClass, anonymous inner class (target_feature)public class TargetClass<T extends CharSequence> extends GenericParentClass<T> {private List<Object> mappedData = new ArrayList<>();
// Target_inner (target_class inner class)public class TargetInner {// Super constructor invocation (feature)public void initialize() {// Implicit super() call to GenericParentClass constructorSystem.out.println("TargetInner initialized");}}
// Target_feature: anonymous inner classprivate final Runnable targetAnonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous task: Mapped data count = " + mappedData.size());}};
public void setMappedData(List<Object> mappedData) {this.mappedData = mappedData;targetAnonymousTask.run(); // Trigger anonymous inner class}
public List<Object> getMappedData() {return new ArrayList<>(mappedData);}
public TargetInner new TargetInner() {return new TargetInner();}}
// Diff package class for ConstructorInvocation pos: diff_package_otherspackage test.refactoring.other;
public class DiffPackageSuperClass {// ClassName.field for ConstructorInvocationpublic static final String STATIC_FIELD = "DiffPackage-Static-Field";
// otherObject.process(this) methodpublic void process(Object source) {System.out.println("DiffPackageSuperClass processed source: " + source);}}
// OtherClass for ConstructorInvocationpackage test.refactoring.movemethod;
public class OtherClass {private String data;
// Constructor for ConstructorInvocation featurepublic OtherClass(String data) {this.data = data;}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5453Test {@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();String[] testItems = {"apple", "banana", "cherry", null};
source.populateTarget(testItems);TargetClass<String> target = source.getTargetField();
// Verify mapped data (lengths of valid items: "apple"→5, "banana"→6, "cherry"→6)List<Object> mappedData = target.getMappedData();assertEquals(3, mappedData.size());assertTrue(mappedData.containsAll(List.of(5, 6, 6)));
// Verify overload_exist (via reflection)try {SourceClass.SourceInner inner = source.new SourceInner();// Overload 1: process(String)inner.getClass().getDeclaredMethod("process", String.class);// Overload 2: process(String, int)inner.getClass().getDeclaredMethod("process", String.class, int.class);} catch (NoSuchMethodException e) {fail("Overloaded methods not found: " + e);}}
@Testvoid testStrictfpBehavior() {SourceClass<CharSequence> source = new SourceClass<>();CharSequence[] testItems = {"123", "4567", "89"};
source.populateTarget(testItems);TargetClass<CharSequence> target = source.getTargetField();
// Verify strictfp does not affect data integrityassertEquals(3, target.getMappedData().size());assertTrue(target.getMappedData().containsAll(List.of(3, 4, 2)));}
@Testvoid testRefactoredMethod() {SourceClass<String> source = new SourceClass<>();TargetClass<String> target = source.getTargetField();String[] testItems = {"orange", "grape"};
// After refactoring: method moved to TargetClass.TargetInnertry {java.lang.reflect.Method refactoredMethod = TargetClass.TargetInner.class.getDeclaredMethod("populateTarget", SourceClass.class, Object[].class);refactoredMethod.setAccessible(true);TargetClass.TargetInner targetInner = target.new TargetInner();refactoredMethod.invoke(targetInner, source, (Object) testItems);
List<Object> mappedData = target.getMappedData();assertEquals(2, mappedData.size());assertTrue(mappedData.containsAll(List.of(6, 5))); // "orange"→6, "grape"→5} catch (Exception e) {fail("Refactored method invocation failed: " + e);}}
@Testvoid testRequiresTryCatch() {SourceClass<String> source = new SourceClass<>();// Pass invalid type (trigger exception handling)try {java.lang.reflect.Method method = SourceClass.class.getDeclaredMethod("populateTarget", Object[].class);method.invoke(source, (Object) new Object[]{123, "valid"}); // 123 is not CharSequence} catch (Exception e) {// Verify exception is caught and logged (no propagation)assertTrue(e.getCause() instanceof IllegalArgumentException);}}}
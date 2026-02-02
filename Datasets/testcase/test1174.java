package test.refactoring.movemethod;
import java.util.ArrayList;
// Source class: generic class, public modifier, no additional featurespublic class SourceClass<T> {// Source contains target field (per_condition)private TargetClass<T> targetField = new TargetClass<>();
// Method to be refactored: private instance method, returns void@Deprecated // has_annotation featureprivate void populateTargetData(T... items) {// Constructor invocation: target class + raw typeTargetClass.RawHelper rawHelper = new TargetClass.RawHelper(); // raw_type featureTargetClass<T>.TargetInnerRec innerRec = targetField.new TargetInnerRec();
// EmptyStatement (private modifier, target_feature: this.field + 1, pos: same_package_target); // Empty statementprivate String fieldRef = innerRec.thisField; // this.field referenceprivate int count = 1; // 1 (target_feature)
// Object initialization + call_method (others_class final method)OtherClass other = new OtherClass() {@Overridefinal void processData(TargetClass<T> target) {this.doProcess(target.getInnerData()); // this.methodName(arguments)}};other.processData(targetField);
// Variable call + access_instance_fieldfor (T item : items) {innerRec.addItem(item); // variable call (innerRec instance)targetField.totalCount += 1; // access_instance_field (targetField's instance field)rawHelper.process(item.toString()); // variable call (raw type instance)}
// Access target inner record's fieldinnerRec.thisField = "Populated-" + items.length;}
// Trigger method for testingpublic void executePopulation(T... items) {populateTargetData(items);}
// Getter for target field to verify data after refactoringpublic TargetClass<T> getTargetField() {return targetField;}}
// Target class: generic class, default modifier, extends super class, local inner classclass TargetClass<T> extends SuperTargetClass {int totalCount = 0; // Instance field for access_instance_fieldprivate ArrayList<T> innerData = new ArrayList<>();
// Target_inner_rec: member inner recordpublic record TargetInnerRec() {String thisField = "initial"; // this.field for EmptyStatement feature
public void addItem(T item) {innerData.add(item); // Access outer class's instance field}}
// Raw type (raw_type feature)public static class RawHelper {public void process(String data) {}}
// Target_feature: local inner classpublic void initLocalInner() {class LocalInnerHandler {public void validateData() {if (innerData.isEmpty()) {throw new IllegalStateException("Data cannot be empty");}}}new LocalInnerHandler().validateData();}
// Getter for inner data (used by call_method)public ArrayList<T> getInnerData() {return innerData;}}
// Super class for target_class extends featureclass SuperTargetClass {}
// Others_class for call_methodclass OtherClass {// Final modifier, normal method, this.methodName(arguments)final void processData(TargetClass<?> target) {}
// Helper method for call_method's this.methodName(arguments)protected void doProcess(ArrayList<?> data) {data.add("processed-by-other");}}
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import java.util.List;
public class MoveMethod5370Test {@Testvoid testOriginalMethod() {SourceClass<String> source = new SourceClass<>();String[] testItems = {"a", "b", "c"};source.executePopulation(testItems);
TargetClass<String> target = source.getTargetField();assertEquals(3, target.totalCount);assertEquals(4, target.getInnerData().size()); // 3 test items + 1 from OtherClassassertEquals("Populated-3", target.new TargetInnerRec().thisField);}
@Testvoid testRefactoredMethod() {SourceClass<Integer> source = new SourceClass<>();TargetClass<Integer> target = source.getTargetField();Integer[] testItems = {1, 2, 3};
// After refactoring: method moved to TargetClass.TargetInnerRecTargetClass<Integer>.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.populateTargetData(source, testItems);
assertEquals(3, target.totalCount);assertEquals(4, target.getInnerData().size());assertEquals("Populated-3", innerRec.thisField);}
@Testvoid testLocalInnerClassValidation() {TargetClass<String> target = new TargetClass<>();assertThrows(IllegalStateException.class, target::initLocalInner);
SourceClass<String> source = new SourceClass<>();source.executePopulation("test");target = source.getTargetField();assertDoesNotThrow(target::initLocalInner);}}
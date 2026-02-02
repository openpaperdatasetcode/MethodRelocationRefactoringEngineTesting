package same.pkg;
import java.lang.reflect.Constructor;import java.lang.reflect.Field;import java.util.ArrayList;import java.util.List;
// TargetClass: private modifier, with type parameter (meets target_class requirements)private class TargetClass<T> {public T targetField;private List<T> dataList = new ArrayList<>();
// Constructor for reflection and normal invocationpublic TargetClass(T field) {this.targetField = field;}
// Overloaded method (meets "overload_exist" feature)public void addData(T data) {dataList.add(data);}
// Overloaded methodpublic void addData(T data, int count) {for (int i = 0; i < count; i++) {dataList.add(data);}}
public List<T> getDataList() {return dataList;}}
// SourceClass: final modifier, with local & member inner class (meets source_class requirements)public final class SourceClass {private String sourceField = "source_base";
// Member inner class (meets "member inner class" feature)public class SourceMemberInner {// Recursive method to be refactored: final, void return, in source_inner (member inner class)public final void recursiveProcessTarget(TargetClass<String> target, int depth) {// Base case for recursionif (depth <= 0) {return;}
// Type declaration statement (meets feature requirement)TargetClass<String> localTarget;try {// ConstructorInvocation: private modifier, access obj.field (meets feature)private Constructor<TargetClass<String>> targetConstructor =TargetClass.class.getConstructor(Object.class);localTarget = targetConstructor.newInstance(sourceField + "_depth-" + depth);
// Used by reflection: access target's fieldField targetField = TargetClass.class.getField("targetField");String fieldVal = (String) targetField.get(target);localTarget.addData(fieldVal); // Variable call: invoke target's method} catch (Exception e) {e.printStackTrace();return;}
// Overload_exist: call overloaded methodlocalTarget.addData(localTarget.targetField, 2);
// Local inner class (meets "local inner class" feature)class LocalInnerProcessor {void process(TargetClass<String> t) {t.addData(sourceField + "_local");}}new LocalInnerProcessor().process(localTarget);
// Recursive call (meets "recursion" method type)recursiveProcessTarget(target, depth - 1);}
// Overloaded recursive method (meets "overload_exist" feature)public final void recursiveProcessTarget(TargetClass<String> target) {recursiveProcessTarget(target, 3); // Default depth = 3}}
// Public method to start recursive processingpublic void startProcessing(TargetClass<String> target) {new SourceMemberInner().recursiveProcessTarget(target);}
// Overloaded start method (meets "overload_exist" feature)public void startProcessing(TargetClass<String> target, int depth) {new SourceMemberInner().recursiveProcessTarget(target, depth);}}
// JUnit Test Case for validating method behavior (minimal necessary test)import org.junit.Test;import static org.junit.Assert.*;
public class SourceClassTest {@Testpublic void testRecursiveProcessTarget() {// ArrangeSourceClass source = new SourceClass();TargetClass<String> target = new TargetClass<>("test_target_field");int testDepth = 2;
// Actsource.startProcessing(target, testDepth);
// Assert: Verify reflection & recursion effects via target's dataassertEquals("Target's data list size should be 2 (from 2 recursion depths)",2, target.getDataList().size());assertTrue("Target's data should contain test field value",target.getDataList().contains("test_target_field"));}}
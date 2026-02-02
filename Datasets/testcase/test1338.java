package test.refactor.movemethod;
import java.util.List;
// Source class (private modifier, same package, member inner + static nested class)private class SourceClass {// Protected field for access_outer_protectedprotected String outerProtectedField = "SourceProtectedValue";
// Feature: static nested classpublic static class SourceStaticNested {public static String staticProcess(String data) {return data + "_staticProcessed";}}
// Feature: member inner class (source_inner - method position)private class SourceInner {/**
Method Javadoc (method javadoc feature)
@param targetParam Target class parameter (per_condition)
@return TargetClass instance (TargetClass Type return)*/private <T> TargetClass<T> processTarget(TargetClass<T> targetParam) {// Per_condition: source contains target's fieldT targetField = targetParam.targetField;if (targetField == null) {return targetParam; // return statement}
// Access_outer_protectedString protectedValue = SourceClass.this.outerProtectedField;
// Variable call: target's static nested class (target_feature)TargetClass.StaticNested.targetStaticMethod(targetField.toString());
// Enhanced for statementList<T> targetList = targetParam.getTargetList();for (T item : targetList) {// Variable call: static nested class methodString processed = SourceStaticNested.staticProcess(item.toString() + "_" + protectedValue);targetParam.addProcessedData(processed);}
try {// Variable call: target's generic method (type parameter feature)targetParam.updateTargetField((T) (targetField.toString() + "_updated"));} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam; // return statement}
// Overloading method (method type: overloading)private <T> TargetClass<T> processTarget(TargetClass<T> targetParam, String extra) {processTarget(targetParam); // Call overloaded methodtargetParam.addProcessedData(extra + "_" + outerProtectedField);return targetParam;}}
// Public method to invoke inner class overloading methodspublic <T> TargetClass<T> invokeProcess(TargetClass<T> target) {SourceInner inner = new SourceInner();return inner.processTarget(target);}
public <T> TargetClass<T> invokeProcess(TargetClass<T> target, String extra) {SourceInner inner = new SourceInner();return inner.processTarget(target, extra);}}
// Target class (default modifier, target_feature: type parameter + static nested class)class TargetClass<T> {// Target field (per_condition)public T targetField;private List<String> processedData;private List<T> targetList;
public TargetClass(T targetField, List<T> targetList) {this.targetField = targetField;this.targetList = targetList;this.processedData = new java.util.ArrayList<>();}
// Target_feature: static nested classpublic static class StaticNested {public static <T> void targetStaticMethod(String data) {System.out.println("Target static processed: " + data);}}
public List<T> getTargetList() {return targetList;}
public void updateTargetField(T field) {this.targetField = field;}
public void addProcessedData(String data) {this.processedData.add(data);}
public List<String> getProcessedData() {return processedData;}}
// Test classpublic class MoveMethodTest5293 {public static void main(String[] args) {SourceClass source = new SourceClass();List<String> testList = List.of("item1", "item2");TargetClass<String> target = new TargetClass<>("testTarget", testList);
// Test first overloaded methodTargetClass<String> result1 = source.invokeProcess(target);System.out.println("Result1 target field: " + result1.targetField);System.out.println("Result1 processed data: " + result1.getProcessedData());
// Test second overloaded methodTargetClass<String> result2 = source.invokeProcess(new TargetClass<>("testTarget2", List.of("item3")), "extraData");System.out.println("Result2 target field: " + result2.targetField);System.out.println("Result2 processed data: " + result2.getProcessedData());}}
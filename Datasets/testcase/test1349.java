package test.refactor.movemethod;
import java.util.List;import java.util.function.Consumer;
// Interface for target_class implements featureinterface TargetProcessable {String processField(String field);}
// Source abstract class (protected, same package, permits + member inner + static nested class)protected sealed abstract class SourceClass permits ConcreteSourceClass {// Static field for ClassName.field featureprivate static final String STATIC_FIELD1 = "SourceStaticField1";private static final String STATIC_FIELD2 = "SourceStaticField2";
// Feature: static nested classpublic static class SourceStaticNested {public static void staticHelper(String data) {System.out.println("StaticHelper: " + data);}}
// Feature: member inner class (source_inner_rec: recursive inner structure)protected class SourceInner {public abstract class RecursiveInner {/**
Abstract method to be refactored (method type: abstract, return type: base type)
@param targetParam Target class parameter (per_condition)
@param dataList Method type parameter: List
@return int (base type)
*/
private abstract int processTarget(TargetClass targetParam, List<String> dataList);
// Recursion method feature (synchronized, source, instanceReference::methodName)public synchronized void recursiveMethod(int depth, TargetClass target) {if (depth <= 0) return;// Recursion: instanceReference::methodName (1 occurrence)Consumer<String> consumer = this::processRecursiveData;consumer.accept(target.getTargetField() + "_depth" + depth);recursiveMethod(depth - 1, target);}
private void processRecursiveData(String data) {SourceStaticNested.staticHelper(data);}}}
// Public method to trigger inner class logicpublic void invokeRecursion(TargetClass target, int depth) {SourceInner inner = new SourceInner();SourceInner.RecursiveInner recursive = inner.new RecursiveInner() {@Overrideprivate int processTarget(TargetClass targetParam, List<String> dataList) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.getTargetField();if (targetField == null) return 0;
// VariableDeclarationStatement (private, source pos, ClassName.field x2)private String field1 = SourceClass.STATIC_FIELD1; // ClassName.field 1private String field2 = SourceClass.STATIC_FIELD2; // ClassName.field 2
// Constructor invocationSourceStaticNested staticNested = new SourceStaticNested();
// Do statementint count = 0;do {String processed = targetParam.processField(targetField + "_do" + count);SourceStaticNested.staticHelper(processed);count++;} while (count < 2);
// Switch statementString switchKey = targetParam.getTargetType();switch (switchKey) {case "TYPE1":SourceStaticNested.staticHelper(field1);break;case "TYPE2":SourceStaticNested.staticHelper(field2);break;default:SourceStaticNested.staticHelper(targetField);}
// Ternary operators with recursion method (pos: ternary operators)Runnable recursionTrigger = (depth > 1) ?() -> this.recursiveMethod(depth, targetParam) :() -> SourceStaticNested.staticHelper("RecursionSkipped");recursionTrigger.run();
// Variable call: target's implemented method (target_feature: implements)String targetProcessed = targetParam.processField(targetField);
// Variable call: list processingdataList.forEach(item -> SourceStaticNested.staticHelper(item + "_" + targetProcessed));
try {return targetField.length() + dataList.size();} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}}};
recursive.recursiveMethod(depth, target);}}
// Concrete subclass of SourceClass (permitted by sealed class)final class ConcreteSourceClass extends SourceClass {}
// Target abstract class (strictfp, target_feature: implements)strictfp abstract class TargetClass implements TargetProcessable {private String targetField;private String targetType;
public TargetClass(String targetField, String targetType) {this.targetField = targetField;this.targetType = targetType;}
public String getTargetField() {return targetField;}
public String getTargetType() {return targetType;}
@Overridepublic abstract String processField(String field);}
// Concrete subclass of TargetClassfinal class ConcreteTargetClass extends TargetClass {public ConcreteTargetClass(String targetField, String targetType) {super(targetField, targetType);}
@Overridepublic String processField(String field) {return "TargetProcessed:" + field;}}
// Test classpublic class MoveMethodTest5306 {public static void main(String[] args) {SourceClass source = new ConcreteSourceClass();TargetClass target = new ConcreteTargetClass("testTargetField", "TYPE1");source.invokeRecursion(target, 2);
// Test processTarget method via inner classSourceClass.SourceInner inner = source.new SourceClass.SourceInner();SourceClass.SourceInner.RecursiveInner recursive = inner.new SourceClass.SourceInner.RecursiveInner() {@Overrideprivate int processTarget(TargetClass targetParam, List<String> dataList) {return super.processTarget(targetParam, dataList);}};int result = recursive.processTarget(target, List.of("item1", "item2"));System.out.println("Refactoring test result: " + result);}}
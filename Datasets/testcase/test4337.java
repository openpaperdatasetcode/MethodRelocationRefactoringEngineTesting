package same.pkg;
import java.util.ArrayList;import java.util.List;
/**
Parent class for generic method (matches method.feature: parent_class)
@param <T> Generic type with upper bound (matches with_bounds)
/
class ParentGenericClass<T extends CharSequence> {
/*
Generic method (1 occurrence) for source's object initialization
@param items Input items to process
@return List of processed strings
*/
public List<String> processGenericItems(T... items) {
List<String> result = new ArrayList<>();
for (T item : items) {
result.add(item.toString());
}
return result;
}
}
// Source class: default modifier, with 2 member inner classesclass SourceClass extends ParentGenericClass<String> {// Member inner class 1class SourceInner1 {}
// Member inner class 2class SourceInner2 {}
// Varargs method: protected modifier, returns TargetClass Type, contains target parameter (per_condition)protected TargetClass varargsMethod(TargetClass targetParam, String... args) {// NullPointerException: check target parameter nullityif (targetParam == null) {throw new NullPointerException("TargetClass parameter cannot be null");}
variableCall(targetParam);
// 1. Generic method call (parent_class) in object initializationParentGenericClass<String> parentInstance = new ParentGenericClass<>();List<String> genericResult = parentInstance.processGenericItems(args);targetParam.getInner().setProcessedList(genericResult);
// 2. InfixExpression (default modifier): string concatenationdefault String infix1 = targetParam.getTargetField() + "_infix1";default String infix2 = infix1 + "_infix2";targetParam.setTargetField(infix2);
return targetParam;}
// Variable call (uses target's field/method)private void variableCall(TargetClass param) {String localVar = param.getTargetField();TargetClass.TargetInner targetInner = param.getInner();}}
/**
Target class: private modifier, with javadoc and member inner class (target_feature)
Stores target data and inner class instance*/private class TargetClass {private String targetField;private TargetInner inner;
public TargetClass() {this.inner = new TargetInner();}
public String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}
public TargetInner getInner() {return inner;}
/**
Target's member inner class (target_feature)
Handles processed list storage*/class TargetInner {private List<String> processedList;
public List<String> getProcessedList() {return processedList;}
public void setProcessedList(List<String> processedList) {this.processedList = processedList;}}}
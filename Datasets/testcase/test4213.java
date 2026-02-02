package test;
import java.util.List;import java.util.ArrayList;import java.util.Objects;
public class SourceClass {// Anonymous inner class 1 (matches source_class.feature)private Runnable anonInnerOne = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous Inner 1 running");}};
// Anonymous inner class 2 (matches source_class.feature)private Runnable anonInnerTwo = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous Inner 2 running");}};
// Member inner class (for method_position: source_inner_rec)class SourceInner {// Recursive method (matches "rec" in source_inner_rec)private int recursiveCount(int num) {if (num <= 0) return 0;return num + recursiveCount(num - 1);}
// Refactored method: instance, return Object, default accessObject moveMethod(TargetClass targetParam) {// Check target non-null (implicit variable call)Objects.requireNonNull(targetParam);
// Access target's field (matches per_condition: source contains target's field)String targetFieldVal = targetParam.targetField;TargetClass.TargetInner targetInner = targetParam.new TargetInner();
// Nested normal method in while loop (matches method.features nested normal type)List<String> resultList = new ArrayList<>();int i = 0;while (i < 3) {resultList.addAll(protectedMethodInWhile(targetInner, i));i++;}
// Variable call: use target inner class methodtargetInner.setInnerData(targetFieldVal + recursiveCount(3));return resultList;}
// Nested normal method: protected, return List<String>, pos: whileprotected List<String> protectedMethodInWhile(TargetClass.TargetInner targetInner, int index) {List<String> subList = new ArrayList<>();// obj.m1().m2().m3() call chain (matches method_feature)String chainResult = targetInner.getInnerData().toLowerCase().trim();subList.add(chainResult + "_" + index);return subList;}}}
/**
Javadoc for TargetClass (matches target_class.target_feature: javadoc)
Holds target field and local inner class for refactoring test*/public class TargetClass {// Target field (accessed by source, matches per_condition)String targetField = "TargetDefaultValue";
// Target inner class (matches target class: target_inner)class TargetInner {private String innerData;
public String getInnerData() {return this.innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}}
// Method with local inner class (matches target_class.target_feature: local inner class)public void methodWithLocalInner() {class TargetLocalInner {void localInnerMethod() {System.out.println("Local Inner Class Method");}}new TargetLocalInner().localInnerMethod();}}
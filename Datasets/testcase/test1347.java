// Package for source_class (different from target_class)package src.pkg;
import target.pkg.TargetClass;import java.util.function.Function;
// Source class (default modifier, different package, local inner + static nested class)class SourceClass {// Feature: static nested classstatic class SourceStaticNested {public static String staticHelper(String data) {return data + "_staticHelper";}}
// Feature: member inner class (source_inner - method position)class SourceInner {// First overloaded method (method type: overloading, method types parameter is:none)TargetClass processTarget(TargetClass targetParam) {return processTarget(targetParam, "defaultArg");}
// Second overloaded method (method type: overloading, method types parameter is:none)TargetClass processTarget(TargetClass targetParam, String extraArg) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;if (targetField == null) {return targetParam;}
// If statementif (extraArg == null) {extraArg = "fallbackArg";}
// Expression statementtargetParam.updateField(targetField + "_updated");
// Local inner class (source feature)class LocalInner {// Generic method feature (inner_class, generic, instanceReference::methodName, pos: switch)public <T> Object genericMethod(T data, Function<T, String> mapper) {return mapper.apply(data);}}
try {LocalInner local = new LocalInner();// Switch statement with generic methodString switchKey = targetParam.getTargetType();switch (switchKey) {case "TYPE1":// Generic method: instanceReference::methodName (1 occurrence)Object result1 = local.genericMethod(targetField, SourceStaticNested::staticHelper);targetParam.addData((String) result1);break;default:Object result2 = local.genericMethod(extraArg, String::toUpperCase);targetParam.addData((String) result2);}
// Variable call: target's anonymous inner class (target_feature)Runnable targetAnon = targetParam.createAnonymousAction();targetAnon.run();
// Variable call: target_inner usageTargetClass.TargetInner targetInner = targetParam.new TargetInner();targetInner.process(targetField);} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam; // TargetClass Type return}}
// Public method to invoke inner class overloaded methodspublic TargetClass invokeProcess(TargetClass target) {SourceInner inner = new SourceInner();return inner.processTarget(target);}
public TargetClass invokeProcess(TargetClass target, String extraArg) {SourceInner inner = new SourceInner();return inner.processTarget(target, extraArg);}}
// Package for target_class (different from source_class)package target.pkg;
import java.util.ArrayList;import java.util.List;
// Parent class for target_class extends featureclass TargetParentClass {protected String parentField = "TargetParentField";}
// Target class (default modifier, target_feature: extends + anonymous inner class)class TargetClass extends TargetParentClass {// Target field (per_condition)public String targetField;private List<String> dataList = new ArrayList<>();
public TargetClass(String targetField) {this.targetField = targetField;}
// Target_feature: member inner class (target_inner)public class TargetInner {public void process(String data) {dataList.add("InnerProcessed:" + data);}}
// Target_feature: anonymous inner classpublic Runnable createAnonymousAction() {return new Runnable() {@Overridepublic void run() {System.out.println("TargetAnon: " + targetField + "_" + parentField);}};}
public void updateField(String value) {this.targetField = value;}
public void addData(String data) {this.dataList.add(data);}
public String getTargetType() {return "TYPE1";}
public List<String> getDataList() {return new ArrayList<>(dataList);}}
// Test class (in separate package or same as source/target)package test.pkg;
import src.pkg.SourceClass;import target.pkg.TargetClass;
public class MoveMethodTest5302 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target1 = new TargetClass("testTarget1");TargetClass result1 = source.invokeProcess(target1);System.out.println("Result1 target field: " + result1.targetField);System.out.println("Result1 data list: " + result1.getDataList());
TargetClass target2 = new TargetClass("testTarget2");TargetClass result2 = source.invokeProcess(target2, "customArg");System.out.println("Result2 target field: " + result2.targetField);System.out.println("Result2 data list: " + result2.getDataList());}}
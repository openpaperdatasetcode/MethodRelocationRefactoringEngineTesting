// Target package (different from source package)package com.target;
// Target: protected normal class with member inner class (target_feature)protected class TargetClass extends SuperTargetClass {public String targetField = "targetValue";
// Member inner class (target_class target_feature)public class TargetInner {public TargetInner() {super(); // Super constructor invocation}public String getValue() {return TargetClass.this.targetField;}}}
// Super class for target's inheritanceclass SuperTargetClass {}
// Source package (different from target package)package com.source;
import com.target.TargetClass;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.util.stream.Collectors;
// Interface for source_class's implements featureinterface TestInterface {void implementMethod();}
// Same-package others class for VariableDeclarationStatement posclass SamePackageOthers {public static void process(TargetClass target) {}}
// Others_class for call_methodclass CallOthersClass {// Private instance method (call_method feature)private List<String> mapTargetValues(List<TargetClass> targets) {return targets.stream().map(TargetClass.TargetInner::getValue) // instanceReference::methodName.collect(Collectors.toList());}}
// Source: default normal class (implements + static nested + member inner class)class SourceClass implements TestInterface {// Static nested class (source_class feature)public static class SourceStaticNested {}
// Member inner class (source_inner_rec: recursive inner class structure)protected class FirstInner {}protected class SourceInner extends FirstInner {// Protected varargs method (base type return)protected int varargsMethod(TargetClass... targets) { // Contains target parameter (meets per_condition)// Same_package_others (pos for VariableDeclarationStatement)SamePackageOthers.process(targets[0]);
// VariableDeclarationStatement: transient modifier, target_feature: obj.field + 1transient String fieldVal = targets[0].targetField;
// Super keywordssuper.toString();
// Super constructor invocation (via target's inner class)TargetClass.TargetInner targetInner = targets[0].new TargetInner();
// Variable callvariableCall(targets[0]);
// Used by reflectiontry {Method method = TargetClass.TargetInner.class.getMethod("getValue");String reflectVal = (String) method.invoke(targetInner);} catch (Exception e) {// No new exception thrown}
// Collection operations (pos for call_method)CallOthersClass others = new CallOthersClass();List<TargetClass> targetList = new ArrayList<>(List.of(targets));List<String> callResult = others.mapTargetValues(targetList);
// Depends on source's static nested classSourceStaticNested staticNested = new SourceStaticNested();
return callResult.size(); // Base type return (int)}
private void variableCall(TargetClass target) {TargetClass local = target;local.new TargetInner().getValue();}}
@Overridepublic void implementMethod() {}}
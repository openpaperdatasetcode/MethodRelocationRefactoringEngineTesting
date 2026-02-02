package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {strictfp TargetClass methodToMove(TargetClass.TargetParam param) { // contains target parameter (per_condition)// constructor invocationTargetClass target = new TargetClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();
// variable callnested.staticMethod();String paramVal = param.getValue();
// access_instance_methodtarget.instanceMethod();
// enhanced for statementList<String> list = new ArrayList<>();list.add(paramVal);for (String s : list) {}
// empty statement;
// call_method: target, private, normal, superTypeReference.methodName(arguments), pos:exception throwing statementstry {List<String> result = target.privateTargetMethod(SuperType.superMethod(param));} catch (IllegalArgumentException e) {throw new RuntimeException(e);}
return target;}}
public class TargetClass {// target_feature: static nested classpublic static class StaticNested {void staticMethod() {}}
static class TargetParam {String getValue() { return "param"; }}
void instanceMethod() {}
// call_method target: private normal methodprivate List<String> privateTargetMethod(String arg) {return new ArrayList<>(List.of(arg));}}
class SuperType {public static String superMethod(TargetClass.TargetParam param) { // superTypeReference.methodName(arguments)return param.getValue();}}

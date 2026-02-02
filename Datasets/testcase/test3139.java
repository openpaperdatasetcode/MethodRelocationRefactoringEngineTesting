package test;
import java.util.List;import java.util.ArrayList;
abstract class TargetParent {protected static String parentStaticMethod() {return "parent";}}
abstract class TargetClass extends TargetParent { // target_feature: extends (inherited)String targetField;
public void createLocalInner() {class LocalInner {} // target_feature: local inner classnew LocalInner();}
class TargetInner {} // target_inner}
private class SourceClass extends TargetParent { // source_feature: extends// Source feature: two member inner classesclass MemberInner1 {}class MemberInner2 {// Recursion method (public modifier, 3 as depth, pos: do-while)public void recursiveMethod(int depth, List<String> result) {if (depth <= 0) return;result.add("recursion-" + depth);do {new MemberInner2().recursiveMethod(depth - 1, result); // 3 as initial depth} while (depth > 1);}}
class SourceInner {// method types parameter is:generic + varargsprotected <T> List<String> methodToMove(TargetClass.TargetInner inner, T... args) {List<String> result = new ArrayList<>();
// Super constructor invocationsuper();
// Variable callTargetClass target = new TargetClass() {};target.createLocalInner();String var = target.targetField;
// For statementfor (T arg : args) {result.add(arg.toString());}
// Try statementtry {result.add(var != null ? var : "default");} catch (Exception e) {result.add("error");}
// Recursion call (method_feature)new MemberInner2().recursiveMethod(3, result);
return result;}}
// Static code blocks with call_methodstatic {String staticResult = TargetClass.parentStaticMethod(); // super.methodName()}}
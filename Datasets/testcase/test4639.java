package test;
import java.util.List;import java.util.ArrayList;
class Source {private String outerPrivateField = "privateData";private Target targetField = new Target();
static class StaticNested {int nestedField;}
class Inner {class InnerRec {/**
Recursive method to process target inner class data and collect results
@param depth Recursion depth control
@param targetInner Target's inner class instance
@return Collected List<String> results*/final List<String> recursiveMethod(int depth, Target.Inner targetInner) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
switch (depth) {case 1:variableCall(targetInner);result.add(targetInner.innerField);break;default:result.add(outerPrivateField);result.addAll(recursiveMethod(depth - 1, targetInner));break;}
Target.StaticNested targetStatic = new Target.StaticNested();result.add(String.valueOf(targetStatic.nestedMethod()));targetField.instanceMethod();
return result;}
private void variableCall(Target.Inner targetInner) {String val = targetInner.innerField;Target.StaticNested staticNested = new Target.StaticNested();int staticVal = staticNested.nestedField;}}}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {try {Target.Inner targetInner = targetField.new Inner();Object callResult = Target.processTarget(targetInner);} catch (Exception e) {e.printStackTrace();}}};}}
/**
Target class with static nested class and inner class,
provides instance and static methods for source class interaction*/public class Target {static class StaticNested {int nestedField = 10;
int nestedMethod() {return nestedField * 2;}}
class Inner {String innerField = "targetInnerData";}
void instanceMethod() {}
static Object processTarget(Inner inner) {return inner.innerField;}
static class SubTarget extends StaticNested {@Overrideint nestedMethod() {return super.nestedMethod() + 5;}}}
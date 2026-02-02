package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
private class SourceClass extends ParentClass implements MyInterface {static class StaticNested1 {}static class StaticNested2 {}
/**
Javadoc for instanceMethod
@param targetParam parameter of TargetClass type
@return List<String> result*/@MyAnnotationList<String> instanceMethod(TargetClass targetParam) {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.innerClass = new TargetClass.StaticNested.InnerClass();
int baseVal = nested.innerClass.calculate(super.toString());
List<String> list = new ArrayList<>();try {switch (baseVal) {case 1:list.addAll(targetParam::finalMethod);break;default:list.addAll(targetParam.finalMethod("default"));}} catch (Exception e) {e.printStackTrace();}
targetParam.doAction();return list;}
// Overload method for overload_exist featureList<String> instanceMethod(TargetClass targetParam, String extra) {return new ArrayList<>();}}
protected class TargetClass {static class StaticNested {InnerClass innerClass;
class InnerClass {private int calculate(String superRef) {return superRef.length();}}}
void doAction() {}
public final List<String> finalMethod(String input) {return Arrays.asList(input.split(","));}}
class ParentClass {}
interface MyInterface {}
@interface MyAnnotation {}

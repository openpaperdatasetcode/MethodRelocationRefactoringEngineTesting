package test;
import java.util.ArrayList;import java.util.List;
/**
Javadoc for SourceClass*/public sealed class SourceClass permits SourceSubClass {private TargetClass targetField = new TargetClass();
class MemberInner {}
static class StaticNested {}
/**
Method Javadoc for varargsMethod
@param args varargs parameters
@return List<String> result*/@MyAnnotationprotected List<String> varargsMethod(String... args) {TargetClass.TargetStaticNested.TargetInnerRec innerRec = targetField.new TargetStaticNested().new TargetInnerRec();int var = innerRec.value;variableCall(var);
List rawList = new ArrayList();rawList.add("raw");
int result = (1 > 0) ? new TargetClass().instanceMethod() : 0;
return new ArrayList<>();}
private void variableCall(int val) {}
strictfp List<String> callMethod() {List<String> list = new ArrayList<>();list.add(overloadedMethod(targetField));list.add(super.overloadedMethod("test"));return list;}
protected String overloadedMethod(TargetClass target) {return "target";}
protected String overloadedMethod(String str) {return str;}}
class SourceSubClass extends SourceClass {}
class TargetClass {static class TargetStaticNested {class TargetInnerRec {int value;}}
int instanceMethod() {return 1;}}
@interface MyAnnotation {}

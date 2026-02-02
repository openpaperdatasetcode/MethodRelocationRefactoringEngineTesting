package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class TargetClass {class TargetInner {String data;
TargetInner(String data) {this.data = data;}
String getInnerData() {return data;}}}
private class SourceClass {static class SourceNested1 {}static class SourceNested2 {}
class SourceInner {{List<String> list = super.getSuperList();}
private List<String> getSuperList() {return new ArrayList<>();}
protected Object recursiveMethod(TargetClass.TargetInner targetParam, int depth) {if (depth <= 0) {return targetParam.getInnerData();}
TypeDecl typeDecl = new TypeDecl();TargetClass target = new TargetClass();TargetClass.TargetInner newInner = target.new TargetInner("depth-" + depth);
Function<String, Integer> func = OthersClass::processString;int result = func.apply(newInner.getInnerData());
return recursiveMethod(newInner, depth - 1);}}
class TypeDecl {}}
class OthersClass {static int processString(String s) {return s.length();}}
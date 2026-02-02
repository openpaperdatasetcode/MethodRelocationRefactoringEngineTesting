package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass extends SuperClass {private TargetClass targetField = new TargetClass();
static class StaticNested {class MemberInner {protected Object varargsMethod(TargetClass... targets) {typeDeclaration: {TargetClass.Inner targetInner = targets[0].new Inner();NestedDependency dep = new NestedDependency();}
variableCall(targets);return overloadMethod(1) + overloadMethod("str");}
private void variableCall(TargetClass[] targets) {TargetClass local = targets[0];TargetClass.Inner inner = targetField.new Inner();}
private int overloadMethod(int num) {return num;}
private String overloadMethod(String str) {return str;}
class NestedDependency {final List<String> process() {do {List<String> result = new TargetClass.Inner().overloadMethod();result = new TargetClass.Inner().overloadMethod(10);} while (false);return new ArrayList<>();}}}}}
class TargetClass {class Inner {List<String> overloadMethod() {return new ArrayList<>();}
List<String> overloadMethod(int num) {return new ArrayList<>();}}}
class SuperClass {}
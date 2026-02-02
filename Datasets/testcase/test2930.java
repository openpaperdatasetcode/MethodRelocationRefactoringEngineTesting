package samepkg;
import java.util.List;import java.util.ArrayList;
private class SourceClass {protected String outerProtectedField = "protectedValue";
public static class StaticNestedSource {}
public class MemberInnerSource {}
/**
Processes target class instance and returns TargetClass type
@param targetParam TargetClass parameter (per condition)
@return Processed TargetClass instance*/protected TargetClass processTarget(TargetClass targetParam) {// Variable call: target instance, outer class members, inner classtargetParam.targetField = 100;StaticNestedSource nested = new StaticNestedSource();MemberInnerSource inner = new MemberInnerSource();String outerVal = this.outerProtectedField;
// Access outer protected fieldassert outerVal.equals("protectedValue") : "Outer protected field mismatch";
// Switch statementint type = targetParam.getTargetType();switch (type) {case 1 -> targetParam.staticNestedTarget.process();case 2 -> targetParam.doSomething();default -> {}}
// While statementint count = 0;while (count < 3) {targetParam.targetField++;count++;}
// Call target's private static method in if/else conditionsif (targetParam.targetField > 100) {List<String> result1 = TargetClass.privateStaticMethod(true);} else {List<String> result2 = TargetClass.privateStaticMethod(false);}
return targetParam;}}
package samepkg;
import java.util.List;import java.util.ArrayList;
public class TargetClass extends ParentTargetClass {public int targetField;public StaticNestedTarget staticNestedTarget = new StaticNestedTarget();
public static class StaticNestedTarget {public void process() {}}
public int getTargetType() {return 1;}
public void doSomething() {}
private static List<String> privateStaticMethod(boolean flag) {// Call super class methodsuper.superMethod(flag ? "arg1" : "arg2");return new ArrayList<>();}}
package samepkg;
public class ParentTargetClass {protected void superMethod(String arg) {}}
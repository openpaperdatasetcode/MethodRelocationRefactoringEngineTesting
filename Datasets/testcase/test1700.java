package test;
import java.util.List;import java.util.ArrayList;
public enum SourceEnum {VALUE;
/**
Instance method with various control structures*/final int instanceMethod(AbstractTargetEnum.StaticNested param) {// Enhanced for statementfor (String s : List.of("a", "b", "c")) {variableCall();}
// Labeled statementLabel: {if (param == null) {break Label;}}
// Switch caseswitch (param.toString()) {case "x":param.instanceMethod();break;default:break;}
// Try statementtry {int val = param.hashCode();} catch (Exception e) {// No new exception}
// Overriding methods in if/else conditionsOthersClass.InnerClass obj = new OthersClass.InnerClass();List<String> result;if (obj != null) {result = obj.overriddenMethod();} else if (obj instanceof OthersClass.InnerClass) {result = obj.overriddenMethod(1);} else {result = obj.overriddenMethod("str");}
return 0;}
private void variableCall() {}}
abstract enum AbstractTargetEnum {TYPE;
static class StaticNested {void instanceMethod() {}}}
class OthersClass {static class InnerClass extends ParentClass {@Overridepublic List<String> overriddenMethod() {return new ArrayList<>();}
@Overridepublic List<String> overriddenMethod(int num) {return new ArrayList<>();}
@Overridepublic List<String> overriddenMethod(String str) {return new ArrayList<>();}}}
abstract class ParentClass {public List<String> overriddenMethod() {return new ArrayList<>();}
public List<String> overriddenMethod(int num) {return new ArrayList<>();}
public List<String> overriddenMethod(String str) {return new ArrayList<>();}}
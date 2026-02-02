package test;
import java.util.ArrayList;import java.util.List;import java.util.function.ToIntFunction;
/**
Abstract target class with javadoc and static nested class (target_features)/
abstract class TargetClass {
/*
Instance field for access_instance_field
(javadoc target_feature)
*/
public String instanceField = "target_instance";
public TargetClass() {}
public abstract void doTask();
/**
Final normal call_method (ClassName::methodName)
*/
public final int targetNormalCall(String input) {
return input.length() + instanceField.length();
}
/**
Static nested class (target_feature)
*/
public static class TargetStaticNested {
public static String staticProcess(String input) {
return input.toUpperCase();
}
}
}
/**
Public source class with local inner and static nested classes*/public class SourceClass extends ParentSource {// Static nested class (source_class feature)public static class SourceStaticNested {public static void assist(TargetClass target) {target.doTask();}}
class InnerClass {class InnerRec {/**
Synchronized instance method_feature (1 parameter, source, instance)
*/
public synchronized List<String> instanceHelper(TargetClass target) {
List<String> result = new ArrayList<>();
// super.methodName()
result.add(super.getParentData());
result.add(target.instanceField);
return result;
}
// Overloading method 1public Object process(TargetClass target) {return process(target, "default_param");}
/**
Default access overloading method 2 (position: source_inner_rec)*/public Object process(TargetClass target, String param) {List<String> result = new ArrayList<>();
// Super keywordsString superData = super.getParentData();result.add(superData);
// Access instance fieldString fieldData = target.instanceField;result.add(fieldData);
// Default InfixExpression (numbers=1)String infixResult = param + "_" + fieldData;result.add(infixResult);
// Variable callvariableCall(target);SourceStaticNested.assist(target);
// Constructor invocationTargetClass concreteTarget = new TargetClass() {@Overridepublic void doTask() {}};result.add(concreteTarget.instanceField);
// Instance code blocks (method_feature position)List<String> helperResult;{helperResult = instanceHelper(target);}result.addAll(helperResult);
// Local inner class (source_class feature)class LocalProcessor {public void validate() {if (fieldData.isEmpty()) {throw new IllegalStateException("Instance field empty");}}}new LocalProcessor().validate();
// do-while (call_method position)int callCount = 0;do {// ClassName::methodNameToIntFunction<TargetClass> callMapper = TargetClass::targetNormalCall;int callResult = callMapper.applyAsInt(target, param + "_" + callCount);result.add(String.valueOf(callResult));callCount++;} while (callCount < 1);
result.add(TargetClass.TargetStaticNested.staticProcess(infixResult));return result;}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger methodpublic Object triggerOverload(TargetClass target) {return new InnerClass().new InnerRec().process(target);}}
// Parent class for super.methodName() and super keywordsclass ParentSource {protected String getParentData() {return "parent_source_data";}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetClass target = new TargetClass() {
@Override
public void doTask() {}
};
SourceClass source = new SourceClass();
Object result = source.triggerOverload(target);
assert result instanceof List && ((List<?>) result).size() == 9 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}
{
    "source_class": {
      "type": "enum class",
      "modifier": "default",
      "position": "same package with target class",
      "feature": [
        "member inner class",
        "static nested class"
      ]
    },
    "target_class": {
      "type": "enum class",
      "modifier": "private",
      "target_feature": [
        "javadoc",
        "anonymous inner class"
      ]
    },
    "method": {
      "type": "instance",
      "return_type": "Object",
      "access_modifier": "final",
      "method_position": "source_inner",
      "features": [
        "constructor invocation",
        "empty statement",
        "type declaration statement",
        "variable call",
        "raw_type",
        "depends_on_static_field",
        "no_new_exception"
      ],
      "target class": "target"
    },
    "call_method": {
      "type": "others_class",
      "modifier": "public",
      "target_feature": [
        "normal",
        "ClassName.methodName(arguments)"
      ],
      "pos": "ternary operators",
      "return_typr": "List<String>"
    },
    "per_condition": "the method to be refactored contains the parameter of the target",
    "id": 716
  }
package test;
import java.util.ArrayList;import java.util.List;
// Others class for call_methodclass OthersClass {/**
Public normal call_method (ClassName.methodName(arguments))
*/
public static List<String> othersNormalCall(String... params) {
List<String> result = new ArrayList<>();
for (String param : params) {
result.add("others_" + param);
}
return result;
}
}
/**
Private enum target class with javadoc and anonymous inner class (target_features)/
private enum TargetEnum {
/*
Javadoc target_feature: Enum constant description
*/
TARGET_CONST("target_data");
public static final String STATIC_FIELD = "target_enum_static"; // For depends_on_static_fieldprivate final String data;
TargetEnum(String data) {this.data = data;}
public void doTask() {}
public String getData() {return data;}
// Anonymous inner class (target_feature)public Runnable getTargetTask() {return new Runnable() {@Overridepublic void run() {System.out.println("Target enum anonymous task: " + data);}};}}
/**
Default enum source class with member inner and static nested classes*/enum SourceEnum {SOURCE_CONST("source_data");
private final String sourceData;
SourceEnum(String sourceData) {this.sourceData = sourceData;}
// Static nested class (source_class feature)public static class SourceStaticNested {public static void assist(TargetEnum target) {target.doTask();}}
// Member inner class (source_class feature)class InnerClass {/**
Final instance method (position: source_inner)*/public final Object process(TargetEnum target) {List<String> result = new ArrayList<>();
// Depends on static fieldresult.add(TargetEnum.STATIC_FIELD);
// Raw typeList rawList = new ArrayList();rawList.add(target.getData());
// Type declaration statementclass LocalProcessor {public String combineData() {return sourceData + "" + target.getData() + "" + TargetEnum.STATIC_FIELD;}}String combined = new LocalProcessor().combineData();result.add(combined);
// Variable callvariableCall(target);SourceStaticNested.assist(target);target.getTargetTask().run();
// Constructor invocation (enum constant as constructor-derived instance)TargetEnum anotherTarget = TargetEnum.TARGET_CONST;result.add(anotherTarget.getData());
// Empty statement;
// Ternary operators (call_method position)List<String> callResult = (combined.length() > 10)? OthersClass.othersNormalCall(target.getData(), sourceData): OthersClass.othersNormalCall("default_param");result.addAll(callResult);
// Verify source contains target's field (per_condition)result.add(target.getData() + "_verified");
return result;}
private void variableCall(TargetEnum target) {target.doTask();}}
// Trigger methodpublic Object triggerProcess(TargetEnum target) {return new InnerClass().process(target);}}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) {
TargetEnum target = TargetEnum.TARGET_CONST;
SourceEnum source = SourceEnum.SOURCE_CONST;
Object result = source.triggerProcess(target);
assert result instanceof List && ((List<?>) result).size() == 7 : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}
import java.lang.reflect.Method; import java.util.ArrayList; import java.util.List; import java.util.function.Supplier;
// Target public normal class (with local inner class)
public class TargetClass {
private String targetField;

public TargetClass(String fieldValue) {
this.targetField = fieldValue;
// Local inner class (target_feature)
class TargetLocalInner {
List<String> extractData(String input) {
List<String> result = new ArrayList<>();
result.add(input + "_local-extracted");
return result;
}
}
new TargetLocalInner().extractData(targetField);
}

// Instance method for inner_class method calls
public List<String> processTargetData(String data) {
List<String> result = new ArrayList<>();
result.add(data + "_target-processed");
return result;
}

public String getTargetField() {
return targetField;
}
}

// Source final normal class (same package, with member & local inner classes)
final class SourceClass {
// Member inner class (source_class feature)
public class SourceMemberInner extends TargetClass {
public SourceMemberInner(String fieldValue) {
super(fieldValue);
}

// Overridden method (for override_violation)
@Override
public List<String> processTargetData(String data) {
// super.methodName(arguments): call parent (TargetClass) method
List<String> parentResult = super.processTargetData(data);
parentResult.add(data + "_inner-overridden");
return parentResult;
}
}

// Recursive, synchronized method (return Object, to be moved)
// Per_condition: contains TargetClass parameter
public synchronized Object recursiveProcess(TargetClass targetParam, int depth) {
// Type declaration statement
SourceMemberInner innerInstance = new SourceMemberInner(targetParam.getTargetField());
List<String> processedList = new ArrayList<>();

// Lambda expressions: 3 inner_class instance method calls (method_feature)
// 1st inner_class method call
Supplier<List<String>> lambda1 = () -> innerInstance.processTargetData("lambda1-" + depth);
// 2nd inner_class method call
Supplier<List<String>> lambda2 = () -> innerInstance.processTargetData("lambda2-" + depth);
// 3rd inner_class method call
Supplier<List<String>> lambda3 = () -> super.processTargetData("lambda3-" + depth); // super call to TargetClass

processedList.addAll(lambda1.get());
processedList.addAll(lambda2.get());
processedList.addAll(lambda3.get());

// Switch statement with 2 CaseDefaultExpression
String switchInput = targetParam.getTargetField();
switch (switchInput.length() % 3) {
case 0:
processedList.add("case-0: " + switchInput);
break;
case 1:
processedList.add("case-1: " + switchInput);
break;
// 1st CaseDefaultExpression
default:
processedList.add("default-1: " + switchInput);
// Nested switch for 2nd CaseDefaultExpression
switch (depth % 2) {
case 0:
processedList.add("nested-case-0: " + depth);
break;
// 2nd CaseDefaultExpression
default:
processedList.add("nested-default-2: " + depth);
break;
}
break;
}

// Variable call: use target parameter method
processedList.addAll(targetParam.processTargetData("var-call-" + depth));

// Recursive base case
if (depth <= 0) {
// Used_by_reflection: access target method via reflection
try {
Method reflectMethod = TargetClass.class.getMethod("processTargetData", String.class);
Object reflectedResult = reflectMethod.invoke(targetParam, "reflection-call");
processedList.addAll((List<String>) reflectedResult);
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
e.printStackTrace();
}
return processedList;
}

// Recursive call
return recursiveProcess(targetParam, depth - 1);
}

// Method with local inner class (source_class feature)
public void useLocalInner() {
class SourceLocalInner {
void log(List<String> data) {
System.out.println("Log size: " + data.size());
}
}
TargetClass tempTarget = new TargetClass("local-test");
Object result = recursiveProcess(tempTarget, 1);
new SourceLocalInner().log((List<String>) result);
}

// Override_violation: SourceClass is final (cannot be extended, violating override potential)
private void preventExtension() {}
}
package test.refactoring;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestAnnot {}

public class SourceClass {
private String sourceInstanceField = "source_instance_data";

class SourceMemberInner {
public String getInnerField() {
return "member_inner_" + sourceInstanceField;
}
}

@RefactorTestAnnot
default String instanceMethod(TargetClass targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}

class SourceLocalInner {
public String processTarget(TargetClass target) {
return target.targetInstanceField + "_local_processed";
}
}

SourceMemberInner memberInner = new SourceMemberInner();
SourceLocalInner localInner = new SourceLocalInner();
String result = "";
int count = 0;

assert targetParam.targetInstanceField != null : "Target's instance field must not be null";
assert memberInner.getInnerField() != null : "Member inner field must not be null";

do {
try {
String targetData = targetParam.targetInstanceField;
result = localInner.processTarget(targetParam);

switch (count) {
case 0:
result += "|case_0|" + sourceInstanceField;
break;
case 1:
result += "|case_1|" + memberInner.getInnerField();
break;
default:
result += "|case_default|" + targetData;
}

variableCall(targetParam, "Count: " + count + ", Intermediate Result: " + result);
count++;
} catch (Exception e) {
result = "error_processing";
break;
}
} while (count < 3);

return result;
}

private void variableCall(TargetClass target, String message) {
System.out.printf("Variable Call Log: %s | Target Field: %s%n",
message, target.targetInstanceField);
}
}

protected class TargetClass {
String targetInstanceField;

static class TargetStaticNested {
public static String staticProcess(String input) {
return "static_nested_" + input;
}
}

public TargetClass(String targetInstanceField) {
this.targetInstanceField = targetInstanceField;
}
}


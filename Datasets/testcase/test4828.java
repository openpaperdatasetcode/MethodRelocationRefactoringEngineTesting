package test.refactoring;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.function.Function;

abstract class ParentSource {
protected String protectedField = "parent_protected_data";

public abstract String overridingMethod(TargetClass.TargetInner target) throws IOException;
}

final class SourceClass extends ParentSource {
private TargetClass targetField;

class SourceMemberInner1 {
private String innerData1;

private SourceMemberInner1(String data) {
this.innerData1 = data;
}

public String getInnerData1() {
return innerData1;
}
}

class SourceMemberInner2 {
private int innerCount = 0;

public int incrementCount() {
return ++innerCount;
}
}

public SourceClass(TargetClass targetField) {
this.targetField = targetField;
}

@Override
public String overridingMethod(TargetClass.TargetInner target) throws IOException {
if (target == null) {
throw new NullPointerException("TargetInner parameter cannot be null");
}

SourceMemberInner1 inner1 = new SourceMemberInner1(
new SourceMemberInner2()::incrementCount + "_" + super.protectedField
);

Function<String, String> exprMethodRef = String::toUpperCase;
String processedRef = exprMethodRef.apply(target.getTargetData());

int count = 0;
do {
ByteArrayInputStream stream = new ByteArrayInputStream(
(inner1.getInnerData1() + "-" + processedRef).getBytes()
);
byte[] buffer = new byte[stream.available()];
int read = stream.read(buffer);
if (read == -1) {
break;
}
count = new SourceMemberInner2().incrementCount();
variableCall(target, new String(buffer));
} while (count < 3);

return super.protectedField + "|" + target.getTargetData() + "|" + inner1.getInnerData1();
}

private void variableCall(TargetClass.TargetInner target, String message) {
System.out.printf("Variable call: %s | Target data: %s%n",
message, target.getTargetData());
}
}

public class TargetClass {
class TargetInner {
private String targetData;

public TargetInner(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}

public TargetClass() {}
}
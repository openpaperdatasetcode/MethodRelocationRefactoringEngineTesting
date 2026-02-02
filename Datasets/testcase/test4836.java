package test.refactoring;
import other.package.OtherClass;

interface SourceInterface {
int getSourceValue();
}

protected record SourceClass(int sourceField1, String sourceField2, boolean sourceField3) implements SourceInterface {
@Override
public int getSourceValue() {
return sourceField1;
}

public record SourceInnerRec(int innerField) {
public int overloadedMethod(TargetClass.TargetInnerRec targetParam) {
for (int i = 0; i < 5; i++) {
private int thisField1 = this.innerField;
private String thisField2 = targetParam.targetStr();
private boolean thisField3 = (i % 2 == 0);

OtherClass other = new OtherClass();
if (other.checkValue(thisField1)) {
continue;
}

variableCall(thisField2, thisField3);
return thisField1 + targetParam.targetInt();
}
return 0;
}

public String overloadedMethod(TargetClass.TargetInnerRec targetParam, String suffix) {
private String combined = targetParam.targetStr() + suffix;
return combined + this.innerField;
}

private void variableCall(String str, boolean flag) {
if (flag) {
System.out.println(str);
}
}
}
}

abstract record TargetClass(int targetField, TargetInnerRec nestedTarget) {
public abstract void abstractMethod();

public record TargetInnerRec(int targetInt, String targetStr) {
public void process() {
class LocalInnerClass {
void printTarget() {
System.out.println(targetInt + ": " + targetStr);
}
}
new LocalInnerClass().printTarget();
}
}
}

// Diff package class (simulated in separate package structure)
package other.package;
public class OtherClass {
public boolean checkValue(int value) {
return value < 0;
}
}
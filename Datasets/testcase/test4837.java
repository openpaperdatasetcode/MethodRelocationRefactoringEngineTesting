package test.refactoring;
import java.util.ArrayList;
import java.util.List;

sealed class ParentSource permits SourceClass {
protected String outerProtectedField = "parent_protected_value";
}

private class SourceClass extends ParentSource {
private TargetClass.TargetInnerRec targetField;

class MemberInnerClass {
private String innerField1 = "inner_field1";
private int innerField2 = 100;

public MemberInnerClass() {}

public String getInnerField1() {
return innerField1;
}

public int getInnerField2() {
return innerField2;
}
}

private Runnable anonymousInner = new Runnable() {
@Override
public void run() {
MemberInnerClass inner = new MemberInnerClass();
System.out.println(inner.getInnerField1());
}
};

public List<String> instanceMethod() {
List<String> result = new ArrayList<>();
MemberInnerClass innerObj = new MemberInnerClass();

do {
private TargetClass.TargetInnerRec targetInner = new TargetClass.TargetInnerRec(
innerObj.getInnerField1(),
innerObj.getInnerField2()
);
targetField = targetInner;

result.add(super.outerProtectedField);
result.add(targetField.getTargetField1());
result.add(String.valueOf(targetField.getTargetField2()));

anonymousInner.run();
break;
} while (targetField.getTargetField2() < 200);

return result;
}
}

public class TargetClass {
static class TargetInnerRec {
private String targetField1;
private int targetField2;

public TargetInnerRec(String field1, int field2) {
this.targetField1 = field1;
this.targetField2 = field2;
}

public String getTargetField1() {
return targetField1;
}

public int getTargetField2() {
return targetField2;
}
}
}
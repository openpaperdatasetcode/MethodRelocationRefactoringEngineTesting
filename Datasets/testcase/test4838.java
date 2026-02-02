package test.refactoring;
public class SourceClass {
private int field1 = 10;
private String field2 = "source";
private boolean field3 = true;

static class SourceStaticNested {
void nestedMethod() {}
}

class SourceMemberInner {
public TargetClass.TargetInner varargsMethod(TargetClass.TargetInner... targets) {
if (targets == null || targets.length == 0) {
return new TargetClass.TargetInner();
}

private final Object lock = new Object();
synchronized (lock) {
this.field1 = targets[0].getInnerField();
this.field2 = targets[0].innerMethod("update");
this.field3 = targets[0].isInnerFlag();
}

SourceStaticNested staticNested = new SourceStaticNested();
staticNested.nestedMethod();

TargetClass.TargetInner result = targets[0];
result.innerMethod("process");
return result;
}

private int field1;
private String field2;
private boolean field3;

private int getInnerField() {
return field1;
}

private String innerMethod(String param) {
return field2 + param;
}

private boolean isInnerFlag() {
return field3;
}
}
}

sealed class TargetClass permits TargetSubClass {
class TargetInner {
private int innerField = 20;
private String innerStr = "target";
private boolean innerFlag = false;

public int getInnerField() {
return innerField;
}

public String innerMethod(String param) {
return innerStr + param;
}

public boolean isInnerFlag() {
return innerFlag;
}
}

Runnable anonymousInner = new Runnable() {
@Override
public void run() {
TargetInner inner = new TargetInner();
System.out.println(inner.getInnerField());
}
};
}

non-sealed class TargetSubClass extends TargetClass {}
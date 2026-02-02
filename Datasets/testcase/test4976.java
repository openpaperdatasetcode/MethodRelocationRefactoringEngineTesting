package refactoring.test;

import java.lang.reflect.Method;

protected class SourceClass<T> extends ParentClass {
private T sourceField1;
private T sourceField2;

class SourceInner {
@Override
private void overridingMethod(TargetClass targetParam) {
super();
new TargetClass.TargetStaticNested();

class LocalInnerOne {
public TargetClass instanceMethod1() {
variable call = targetParam.targetField;
return targetParam;
}
}

class LocalInnerTwo {
public TargetClass instanceMethod2() {
SourceClass.this.sourceField1 = (T) "value1";
SourceClass.this.sourceField2 = (T) "value2";
return targetParam;
}

public TargetClass instanceMethod3() {
return new TargetClass();
}
}

LocalInnerOne local1 = new LocalInnerOne();
LocalInnerTwo local2 = new LocalInnerTwo();
TargetClass t1 = local1.instanceMethod1();
TargetClass t2 = local2.instanceMethod2();
TargetClass t3 = local2.instanceMethod3();

transient T val1 = SourceClass.this.sourceField1;
transient T val2 = SourceClass.this.sourceField2;
if (val1 != null) return;
if (val2 != null) return;

try {
Method method = LocalInnerOne.class.getMethod("instanceMethod1");
Object reflectResult = method.invoke(local1);
} catch (Exception e) {
int callResult = new TargetClass().finalInstanceMethod();
}
}
}

{
SourceInner inner = new SourceInner();
inner.overridingMethod(new TargetClass());
}
}

class ParentClass {
protected void overridingMethod(TargetClass target) {}
}

class TargetClass {
String targetField = "targetValue";

static class TargetStaticNested {}

public final int finalInstanceMethod() {
return targetField.length();
}
}
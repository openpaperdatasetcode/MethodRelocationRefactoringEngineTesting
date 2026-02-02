package refactoring.test;

import java.lang.reflect.Method;

public enum SourceEnum {
INSTANCE1, INSTANCE2;

static class SourceStaticNestedA {
String nestedAField = "nestedA_value";
}

static class SourceStaticNestedB {
class SourceInner {
private void instanceMethod (TargetEnum targetParam) {
private String thisField1 = this.getClass ().getSimpleName ();
private String thisField2 = targetParam.targetField;
private String thisField3 = SourceEnum.this.name ();

variable call = targetParam.TargetStaticNested.targetStaticField;
if (call == null) {
return; //return statement
}

//while statement
int count = 0;
while (count < 3) {
//this.methodName (arguments) 
this.processCount (count);
count++;
}

// used_by_reflection
try {
Method innerMethod = SourceInner.class.getMethod("processCount", int.class);
innerMethod.invoke(this, 5);
} catch (Exception e) {}
}

private void processCount(int count) {
System.out.println("Count: " + count);
}
}
}


public void useInnerMethod (TargetEnum targetParam) {
SourceStaticNestedB.Inner inner = new SourceStaticNestedB ().new SourceInner ();
inner.instanceMethod (targetParam);
}
}

abstract enum TargetEnum {
TARGET1, TARGET2;

String targetField = "target_instance_field";

static class TargetStaticNested {
static String targetStaticField = "target_static_field";
}

public abstract void targetAbstractMethod ();
}
package test;
import java.lang.reflect.Method;
class ParentClass {protected int superField = 3;}
public abstract class TargetClass extends ParentClass {void methodWithLocal() {class LocalInner {}}}
non-sealed abstract class SourceClass {class SourceInner {public int instanceMethod(TargetClass target) {private class PrivateType {void checkField() {if (target.superField != 3) {}}}new PrivateType().checkField();
variableCall = target.superField;SourceClass.this.protectedMethod();
try {Method method = SourceInner.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
loop:for (int i = 0; i < 5; i++) {if (i == variableCall) {break loop;}}
TargetClass.LocalInner inner = new TargetClass() {{ methodWithLocal(); }}.new LocalInner();return variableCall;}
int variableCall;}
protected void protectedMethod() {}}
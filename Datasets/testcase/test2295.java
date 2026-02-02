package test;
import java.lang.reflect.Method;
strictfp class SourceClass {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
class SourceInner {protected TargetClass instanceMethod(TargetClass target) {variableCall = target.field;target.instanceMethod();
private TargetClass privateReturn() {if (target.superField == 1) {return target;}return null;}
try {Method method = SourceInner.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
for (int i = 0; i < 1; i++) {TargetClass result = this.publicMethod(target);}
return privateReturn();}
public TargetClass publicMethod(TargetClass target) {return target;}
TargetClass variableCall;}}
class ParentTarget {int superField;}
class TargetClass extends ParentTarget {int field;
void instanceMethod() {}}
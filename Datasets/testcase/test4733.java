package test;
import java.util.List;import java.lang.reflect.Constructor;
class ParentClass {int superField;}
private class SourceClass {class MemberInner {}
public int moveMethod(TargetClass.Inner.InnerRec target, List<String> list) {class LocalType {}LocalType lt = new LocalType();
ParentClass pc = new ParentClass();private int val1 = pc.superField;private int val2 = pc.superField;
for (String s : list) {variableCall(target);target.instanceMethod();}
switch (target.field) {case 1:break;default:break;}
try {Constructor<?> ctor = TargetClass.Inner.InnerRec.class.getConstructor();ctor.newInstance();} catch (Exception e) {}
Object outerThis = SourceClass.this;return TargetClass.staticField;}
private void variableCall(TargetClass.Inner.InnerRec t) {}}
protected class TargetClass extends ParentClass {static int staticField;
class Inner {class InnerRec {int field;
void instanceMethod() {}}}
Object anonymous = new Object() {};}
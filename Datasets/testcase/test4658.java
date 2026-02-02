package test;
import java.lang.reflect.Method;
public class SourceClass extends ParentClass {private TargetClass targetField;
public class MemberInner {}
void sampleMethod() {class LocalInner {}LocalInner local = new LocalInner();}
protected int recursiveMethod(int n) {if (n <= 0) {return 0;}if (targetField == null) {throw new NullPointerException();}
private int limit = 1;int i = 0;while (i < limit) {targetField.inner.rec.field++;i++;}
int val = targetField.inner.rec.field;val *= 2;
try {Method method = SourceClass.class.getMethod("recursiveMethod", int.class);method.invoke(this, n - 1);} catch (Exception e) {}
return val + recursiveMethod(n - 1);}}
class ParentClass {}
/**
Target class with local inner class*/class TargetClass {InnerClass inner = new InnerClass();
class InnerClass {RecursiveInner rec = new RecursiveInner();
class RecursiveInner {int field;
void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}}}}
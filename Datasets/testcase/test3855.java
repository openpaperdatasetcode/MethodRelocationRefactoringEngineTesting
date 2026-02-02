package test.refactoring;
import java.lang.reflect.Method;
private class SourceClass {private transient int field1;private transient int field2;
public static class FirstStaticNested {protected Object baseMethod() {return "baseValue";}}
public static class SecondStaticNested extends FirstStaticNested {@Overrideprotected Object baseMethod() {return super.baseMethod() + "-overridden";}}
public TargetClass process(TargetClass target) {super.toString();
synchronized (target) {this.field1 = 10;this.field2 = 20;
for (int i = 0; i < 5; i++) {if (i == 2) {break;}target.getInner().updateCount(i);}}
TargetClass.InnerClass inner = new TargetClass.InnerClass(this.field1, new SecondStaticNested().baseMethod());assert inner.getCount() > 0 : "Count must be positive";
variableCall(target, inner);
try {Method method = TargetClass.class.getMethod("getInner");method.invoke(target);} catch (Exception e) {}
return target;}
private void variableCall(TargetClass target, TargetClass.InnerClass inner) {target.setInner(inner);this.field1 = inner.getCount();this.field2 = inner.getCount() * 2;}}
public class TargetClass {private InnerClass inner;
public InnerClass getInner() {return inner;}
public void setInner(InnerClass inner) {this.inner = inner;}
class InnerClass {private int count;private Object data;
public InnerClass(int initialCount, Object data) {this.count = initialCount;this.data = data;}
public void updateCount(int val) {this.count += val;}
public int getCount() {return count;}}}
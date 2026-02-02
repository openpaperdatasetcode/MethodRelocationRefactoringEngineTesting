package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
strictfp class SourceClass<T> {public class SourceInner {TargetClass<T> varargsMethod(TargetClass<T> target, T... args) {class LocalInner1 {void checkSuperFields() {loop: for (int i = 0; i < 3; i++) {if (target.superField1 > 0) break loop;if (target.superField2 > 0) break loop;if (target.superField3 > 0) break loop;}}}new LocalInner1().checkSuperFields();
class LocalInner2 {}
OtherClass other = new OtherClass();List<String>[] lists = new List[]{other.getList().subList(0, 1).isEmpty() ? new ArrayList<>() : new ArrayList<>()};
switch (args.length) {case 0: break;default: break;}
target.value = args[0];
try {Method method = TargetClass.class.getMethod("method");method.invoke(target);} catch (Exception e) {}
SourceInner inner = new SourceInner();inner.toString();
return target;}}}
private class TargetClass<T> extends SuperClass {T value;
public void method() {}}
class SuperClass {protected int superField1;protected int superField2;protected int superField3;}
class OtherClass {public List<String> getList() {return new ArrayList<>();}}
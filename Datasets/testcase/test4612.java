package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface TestInterface {}
private class SourceClass implements TestInterface {TargetClass targetParam;
final List<String> SourceClass(TargetClass param) {this.targetParam = param;super();Label: {while (true) {new SourceClass(1);break Label;}}try {Method method = SourceClass.class.getMethod("method");method.invoke(this);} catch (Exception e) {}TargetClass.InnerClass inner = param.new InnerClass();inner.method();Object obj = new Object() {};class LocalInner {}new LocalInner();return new ArrayList<>();}
private SourceClass(int num) {this.targetParam.field = 1;}
public Object method() {return null;}
public Object method(String s) {return new SourceClass(targetParam).method();}
public Object method(int a, int b) {return this.method();}}
protected class TargetClass {int field;
class InnerClass {void method() {}}}
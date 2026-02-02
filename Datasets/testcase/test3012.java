package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class SourceClass implements MyInterface {private String privateField = "sourcePrivate";
class InnerClass1 {}class InnerClass2 {}
private List<String> instanceMethod(TargetClass targetParam) {List<String> result = new ArrayList<>();targetParam.inner.doSomething();result.add(this.privateField);
try {Method method = TargetClass.InnerClass.class.getMethod("doSomething");} catch (NoSuchMethodException e) {e.printStackTrace();}
return result;}}
abstract class TargetClass {class InnerClass {void doSomething() {}}
InnerClass inner = new InnerClass();}
interface MyInterface {}
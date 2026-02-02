package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.util.function.Function;
interface TestInterface {}
abstract class SourceClass {static class StaticNested {}
class MemberInner {protected Object methodToMove(TargetClass<String> targetParam) {targetParam.field1 = "value1";targetParam.field2 = 20;targetParam.field3 = new Object();
TargetClass<String> newTarget = new TargetClass<>();List<String> items = new ArrayList<>();
for (String item : items) {if (item.isEmpty()) {continue;}targetParam.variableCall();}
int num = 1;switch (num) {case 1:Function<String, TargetClass<String>> func = s -> innerOverload(s);TargetClass<String> result = func.apply("test");break;default:break;}
try {Method refMethod = TargetClass.MemberInner.class.getMethod("innerMethod");refMethod.invoke(targetParam.new MemberInner());} catch (Exception e) {}
return targetParam;}
protected Object methodToMove(TargetClass<Integer> targetParam, int arg) {return targetParam;}
protected TargetClass<String> innerOverload(String s) {return new TargetClass<>();}
protected TargetClass<String> innerOverload(Integer i) {return new TargetClass<>();}}}
protected class TargetClass<T> implements TestInterface {String field1;int field2;Object field3;
class MemberInner {void innerMethod() {}}
void variableCall() {}}
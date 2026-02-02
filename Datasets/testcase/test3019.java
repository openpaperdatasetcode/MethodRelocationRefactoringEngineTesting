package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
private class SourceClass<T extends ParentClass> {static class StaticNested1 {}static class StaticNested2 {}
public final List<String> instanceMethod(TargetClass<String> targetParam) {TargetClass.MemberInner inner = targetParam.new MemberInner();
String[] array = {"a", "b", "c"};SubClass sub = new SubClass();List<String> combined = new ArrayList<>();combined.addAll(sub.method1(array));combined.addAll(sub.method2(array));combined.addAll(sub.method3(array));
for (String s : array) {inner.doAction(s);}
return combined;}}
protected class TargetClass<V> {class MemberInner {void doAction(String input) {}}}
class ParentClass {}
class SubClass extends ParentClass {public List<String> method1(String[] args) {return new ArrayList<>(Arrays.asList(args));}
public List<String> method2(String[] args) {return new ArrayList<>(Arrays.asList(args));}
public List<String> method3(String[] args) {return new ArrayList<>(Arrays.asList(args));}}

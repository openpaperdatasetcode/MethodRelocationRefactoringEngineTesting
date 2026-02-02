package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface TestInterface {}
protected class SourceClass {public static class StaticNested {}
class MemberInner {}
TargetClass.StaticNested moveMethod(TargetClass target) {static int targetField = target.instanceField;assert targetField > 0 : "Target field must be positive";
TargetClass.StaticNested nested = new TargetClass.StaticNested();int i = 0;while (i < 3) {nested.process(targetField + i);this.helperMethod(i);i++;}
Function<String, Integer> lengthMapper = String::length;
List<String> result = targetField > 2 ? OthersClass.staticMethod1((s) -> s.toUpperCase()) : OthersClass.staticMethod2((s) -> s.toLowerCase());
return nested;}
private void helperMethod(int num) {}}
protected class TargetClass implements TestInterface {public int instanceField = 10;
public static class StaticNested {public void process(int val) {}}}
class OthersClass {protected static List<String> staticMethod1(Function<String, String> mapper) {List<String> list = new ArrayList<>();list.add(mapper.apply("method1"));return list;}
protected static List<String> staticMethod2(Function<String, String> mapper) {List<String> list = new ArrayList<>();list.add(mapper.apply("method2"));return list;}
private List<String> callMethod(TargetClass target) {if (target.instanceField > 5) {return new SourceClass().new MemberInner().invokeInnerMethod(target);} else {return new ArrayList<>();}}
private class Helper {List<String> invoke(TargetClass target) {return new SourceClass().new MemberInner().invokeInnerMethod(target);}}}
class SourceClass$MemberInner {List<String> invokeInnerMethod(TargetClass target) {List<String> list = new ArrayList<>();list.add(String.valueOf(target.instanceField));return list;}}

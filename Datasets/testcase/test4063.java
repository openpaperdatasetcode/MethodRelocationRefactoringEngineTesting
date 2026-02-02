package test;
import java.util.List;import java.util.ArrayList;
sealed record SourceClass(String s) implements MyInterface permits TargetClass {private static int staticMethod(int a) {return a + 1;}
protected List<String> varargsMethod(TargetClass target, String... args) {assert args != null;char c = 'a';int num = 1;List<String> list = new ArrayList<>();list.add(target.toString());list.add(s);Runnable r = () -> {int res = staticMethod(num);SourceClass.this.varargsMethod(target, "lambda");};return list;}
class LocalInnerClass {void localMethod() {}}
static class StaticNestedClass {static void nestedMethod() {}}}
private record TargetClass(int i) {class MemberInnerClass {void memberMethod() {}}}
interface MyInterface {}
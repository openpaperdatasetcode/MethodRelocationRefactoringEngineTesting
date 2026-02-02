package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {public TargetClass<Integer> targetField;
public static class StaticNestedClass {}
public class MemberInnerClass {}
@MyAnnotation(call = TargetClass.TargetInnerClass.staticMethod().m1().m2().m3())public int varargsMethod(int... args) throws IllegalArgumentException {int sum = 0;if (args.length == 0) {super.toString();return sum;}for (int num : args) {sum += num;}return sum + targetField.someValue;}}
public class TargetClass {
public int someValue;
public class TargetInnerClass {public static TargetInnerClass staticMethod() {return new TargetClass<?>.TargetInnerClass();}
public TargetInnerClass m1() {return this;}
public TargetInnerClass m2() {return this;}
public List<String> m3() {return new ArrayList<>();}}}
@interface MyAnnotation {String call();}
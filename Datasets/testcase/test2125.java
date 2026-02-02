package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.List;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
protected class SourceClass {class FirstInner {class InnerRecursive {@MyAnnotationfinal int methodToMove(TargetClass... targets) {TargetClass.StaticNested rawNested = new TargetClass.StaticNested();
int total = 0;for (TargetClass target : targets) {TargetClass.StaticNested<Integer> genericNested = new TargetClass.StaticNested<>();total += target.new InnerClass().variableCall();}
Function<String, Integer> func1 = TargetClass.StaticNested::parse;Function<Double, Integer> func2 = TargetClass.StaticNested::convert;int result = (total > 0) ? func1.apply("10") : func2.apply(5.0);
try {Method method = TargetClass.InnerClass.class.getMethod("variableCall");method.invoke(targets[0].new InnerClass());} catch (Exception e) {}
return result;}}}}
public class TargetClass {static class StaticNested<T> {static int parse(String s) {return Integer.parseInt(s);}
static int convert(Double d) {return d.intValue();}}
class InnerClass {int variableCall() {return 1;}}}
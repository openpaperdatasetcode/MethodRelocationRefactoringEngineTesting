package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default "";String methodCall() default "";}
public class SourceClass<T> extends ParentClass {private static class StaticNestedSource {}
public void varargsMethod(TargetClass.TargetInnerRec... targetParams) {if (targetParams == null) {throw new NullPointerException();}
class LocalInner {}LocalInner local = new LocalInner();
try {new SubClass().genericMethod(1, "test");} catch (Exception e) {}
super();TargetClass.TargetInnerRec var = targetParams[0];var.call();}
public <U, V> Object genericMethod(U u, V v) {this.varargsMethod(new TargetClass.TargetInnerRec());return new Object();}
private class InnerCaller {@CallAnnotation(methodCall = "#{callMethod()}")private List<String> callMethod() {SourceClass.this.varargsMethod(new TargetClass.TargetInnerRec());super.toString();return new ArrayList<>();}}}
class ParentClass {}
class SubClass extends SourceClass<Integer> {}
private class TargetClass {static class TargetInnerRec {void call() {}}}

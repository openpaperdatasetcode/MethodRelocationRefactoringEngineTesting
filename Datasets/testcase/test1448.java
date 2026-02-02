package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RecursionAnnotation {String lambdaMethod() default "SubTargetClass::recursiveCompute";}
public class SourceClass {protected int outerProtected = 10;
static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
@RecursionAnnotationpublic List<String> process(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.Inner targetInner = target.new Inner();new StaticNested();
SubTargetClass subTarget = new SubTargetClass();int recursiveResult = subTarget.recursiveCompute(targetInner.field);
targetInner.field += SourceClass.this.outerProtected;result.add(String.valueOf(targetInner.field));result.add(String.valueOf(recursiveResult));
TargetClass.RawInner rawInner = new TargetClass.RawInner();result.add(rawInner.getValue());
return result;}}
public class TargetClass {class Inner {int field = 5;}
class RawInner {}}
class SubTargetClass extends TargetClass {private int recursiveCompute (int num) {if (num <= 0) return 1;Runnable recursionLambda = () -> recursiveCompute (num - 1);recursionLambda.run ();return num * recursiveCompute (num - 1);}}
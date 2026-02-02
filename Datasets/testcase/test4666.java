package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default "ParentClass.process()";}
protected class SourceClass extends ParentClass {private TargetClass target;
static class NestedSource {static TargetClass createTarget(int value) {return new TargetClass(value);}}
{new Runnable() {@Overridepublic void run() {try {processValue(target, 5);} catch (IOException e) {e.printStackTrace();}}}.run();}
@Overrideprotected void process() {}
public final int processValue(TargetClass target, int depth) throws IOException {if (depth == 0) {return target.value;}switch (target.value) {case 1:@CallAnnotationint case1 = depth - 1;this.process();break;default:@CallAnnotation("ParentClass.process()")int defaultCase = depth - 1;break;}return processValue(TargetClass.NestedTarget.increment(target), depth - 1);}}
class TargetClass {int value;
TargetClass(int value) {this.value = value;}
static class NestedTarget {static TargetClass increment(TargetClass target) {return new TargetClass(target.value + 1);}}}
class ParentClass {protected void process() throws IOException {}}
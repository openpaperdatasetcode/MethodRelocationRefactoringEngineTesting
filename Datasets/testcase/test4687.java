package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
interface BaseInterface {protected TargetInterface getDefaultTarget();}
interface SourceInterface extends BaseInterface {static class NestedHelperOne {public static TargetInterface createTarget(String data) {return new TargetInterface() {@Overridepublic String getTargetData() {return data;}};}}
static class NestedHelperTwo {public static TargetInterface updateTarget(TargetInterface target, String suffix) {return new TargetInterface() {@Overridepublic String getTargetData() {return target.getTargetData() + suffix;}};}}
default void initInnerProcessor() {class SourceInner {/**
Processes target with single parameter
@param target Target interface instance to process
@return Processed TargetInterface instance
*/
@ProcessAnnotation
default TargetInterface process(TargetInterface target) {
String outerData = SourceInterface.this.getDefaultTarget().getTargetData();
return NestedHelperTwo.updateTarget(target, "_" + outerData);
}
/**
Processes target with two parameters (overload)
@param target Target interface instance to process
@param depth Recursion depth
@return Processed TargetInterface instance
*/
@ProcessAnnotation
default TargetInterface process(TargetInterface target, int depth) {
if (depth <= 0) {
return target;
}
TargetInterface updated = process(target);
return process(updated, depth - 1);
}
}
TargetInterface initialTarget = NestedHelperOne.createTarget("InitialData");SourceInner inner = new SourceInner();TargetInterface finalTarget = inner.process(initialTarget, 3);System.out.println("Final Target Data: " + finalTarget.getTargetData());}
@Overridedefault TargetInterface getDefaultTarget() {return TargetInterface.NestedTarget.create("DefaultData");}}
interface TargetInterface {String getTargetData();
static class NestedTarget {public static TargetInterface create(String data) {return new TargetInterface() {@Overridepublic String getTargetData() {return data;}};}}}
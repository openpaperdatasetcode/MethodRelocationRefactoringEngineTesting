package source;
import target.TargetAnnotation;import java.util.ArrayList;
/**
Source annotation with overloading methods/
public @interface SourceAnnotation {
/*
Overloaded method accepting TargetAnnotation
@param target target annotation instance
@return TargetAnnotation instance
*/
protected TargetAnnotation overloadingMethod(TargetAnnotation target);
/**
Overloaded method accepting String
@param str input string
@return default TargetAnnotation instance
*/
protected TargetAnnotation overloadingMethod(String str);
class NestedHelper {private TargetAnnotation targetField;
public String callSourceMethod(SourceAnnotation ann, TargetAnnotation target) {targetField = ann.overloadingMethod(target);String result = ann.overloadingMethod("test").nestedClass().getMethod().process();return result;}}
default void createInner() {class LocalInner {@MyPrivateAnnotationvoid useTarget(TargetAnnotation target) {System.out.println(super.toString());TargetAnnotation varCall = target;varCall.nestedClass();}}
Runnable anon = new Runnable() {@Overridepublic void run() {new LocalInner().useTarget(new TargetAnnotation() {});}};anon.run();
ArrayList rawList = new ArrayList();rawList.add(targetField);}
TargetAnnotation targetField() default @TargetAnnotation;}
@interface MyPrivateAnnotation {}
package target;
/**
Default target annotation with static nested class*/public @interface TargetAnnotation {static class NestedClass {public Processor getMethod() {return new Processor();}}
class Processor {public String process() {return "processed";}}
NestedClass nestedClass() default @NestedClass;}
package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface StaticMethodAnn {String value() default "Test annotation for static method";}
protected class SourceClass {public static String staticField = "source_static_field";
public class OuterInstance {public String getInstanceData() {return "outer_instance_data";}}
@StaticMethodAnnprotected static void staticMethod(TargetClass target) {// Local inner classclass StaticLocalInner {void process(TargetClass t) {// Variable callString targetData = t.getData();System.out.println("Target data: " + targetData);
// Depends on static fieldString combined = targetData + "_" + staticField;System.out.println("Combined data: " + combined);
// Uses outer this (via outer instance)OuterInstance outer = new SourceClass().new OuterInstance();System.out.println("Outer instance data: " + outer.getInstanceData());}}
StaticLocalInner localInner = new StaticLocalInner();localInner.process(target);}}
class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public String getData() {// Target's local inner classclass TargetLocalInner {String extract() {return data + "_target_local";}}return new TargetLocalInner().extract();}}
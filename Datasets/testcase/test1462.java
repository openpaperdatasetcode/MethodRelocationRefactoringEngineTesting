package test.source;
import test.target.TargetClass;
// Source class: normal, private, different package from target, has anonymous inner/static nested classprivate class SourceClass extends ParentClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private String sourceInstanceField = "source_instance_field";
// Static nested class (source feature)private static class SourceStaticNested {}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {moveTargetMethod();}};
// Target method: normal, void, default access, source positionvoid moveTargetMethod() {// Super constructor invocation (via parent class)super();
// Variable call + access_instance_fieldString var = sourceInstanceField;System.out.println(var);
// TypeDeclarationStatement (private, pos: same_package_target, target_feature: this.field x1)private class LocalType {public void localMethod() {String fieldVal = SourceClass.this.sourceInstanceField; // this.field (target_feature)System.out.println(fieldVal + " - " + targetField.targetInstanceField);}}
// No new checked exceptionnew LocalType().localMethod();}}
// Parent class for super constructor invocationclass ParentClass {}
package test.target;
/**
Target class Javadoc (target_feature: javadoc)*/// Target class: normal, public, has javadoc/static nested class (target_feature)public class TargetClass {// Target instance field (referenced by source - per_condition)public String targetInstanceField = "target_instance_field";
// Static nested class (target_feature)public static class TargetStaticNested {}}
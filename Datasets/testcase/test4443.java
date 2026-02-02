package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface FeatureAnnotation {}
public class Source {private Target targetField = new Target();private int outerPrivateField = 100;
static class StaticNested {int nestedStaticField = 20;}
@FeatureAnnotationpublic Object instanceMethod() {labeledBlock: {expressionStatement(targetField);variableCall(targetField);
Runnable lambda = () -> {SubSource sub = new SubSource ();sub.privateRecursiveMethod (1);};lambda.run ();
break labeledBlock;}
methodWithLocalInner();return targetField.targetField;}
private void expressionStatement(Target target) {target.targetField++;target.staticNestedFieldAccess = Target.StaticNested.targetStaticField;}
private void variableCall (Target target) {int val = target.targetField;val += outerPrivateField; Target.StaticNested nested = new Target.StaticNested ();val += nested.nestedField;}
private void methodWithLocalInner() {class LocalInner {void useTarget() {int localVal = targetField.targetField;localVal += StaticNested.nestedStaticField;}}new LocalInner().useTarget();}
static class SubSource extends Source {private void privateRecursiveMethod (int depth) {if (depth <= 0) {super.instanceMethod (); 
return;}Target.StaticNested nested = new Target.StaticNested ();System.out.println (nested.nestedField);privateRecursiveMethod (depth - 1); }}}
private class Target {int targetField = 10;int staticNestedFieldAccess;
static class StaticNested {static int targetStaticField = 5;int nestedField = 15;}}
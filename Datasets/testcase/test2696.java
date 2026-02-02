package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public abstract class SourceClass extends ParentSourceClass {// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
@MethodAnnotprotected List<String> methodToMove(TargetClass target) {List<String> results = new ArrayList<>();
// Variable call + contains target parameter (per_condition)target.toString();
// Access instance fieldString targetField = target.instanceField;results.add(targetField);
return results;}}
abstract class ParentSourceClass {// Overloading method 1 (parent_class)public synchronized Object callMethod(TargetClass target) {return superTypeCall(target);}
// Overloading method 2 (parent_class)public synchronized Object callMethod(TargetClass target, int arg) {Object result = superTypeCall(target);return new Object[]{result, arg};}
private Object superTypeCall(TargetClass target) {// Overloading + superTypeReference.methodName(arguments) in array initializationSourceClass source = new SourceClass() {};return new Object[]{source.methodToMove(target)};}}
/**
Javadoc for TargetClass (target_feature: javadoc)*/private abstract class TargetClass implements TestInterface {public String instanceField = "targetFieldValue"; // Source contains target's field (per_condition)
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}}
interface TestInterface {}
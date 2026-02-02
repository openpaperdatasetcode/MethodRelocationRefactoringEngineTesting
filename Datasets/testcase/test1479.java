package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
// Source class: enum, protected, same package, has permits/two anonymous inner classesprotected sealed enum SourceEnum permits SourceSubEnum {INSTANCE;
private String sourceVar = "source_variable";
// First anonymous inner class (source feature)private Runnable anonInner1 = new Runnable() {@Overridepublic void run() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod(TargetEnum.TARGET1);}};
// Second anonymous inner class (source feature - duplicate)private Runnable anonInner2 = new Runnable() {@Overridepublic void run() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod(TargetEnum.TARGET2);}};
// Member inner class (parent of source_inner_rec)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: instance, List<String>, private, source_inner_rec position// per_condition: contains target parameter (TargetEnum)private List<String> moveTargetMethod(TargetEnum targetParam) {List<String> result = new ArrayList<>();String var = sourceVar; // variable call
// Type declaration statementclass LocalType {String localField = var + targetParam.targetField;}LocalType local = new LocalType();
// Labeled statementlabeledBlock: {// Switch caseswitch (targetParam) {case TARGET1:result.add(local.localField + "_case1");break labeledBlock;case TARGET2:result.add(local.localField + "_case2");break labeledBlock;default:result.add(local.localField + "_default");}}
// Used by reflectiontry {Method method = SourceInnerRecClass.class.getDeclaredMethod("recursiveMethod", TargetEnum.class, int.class);method.setAccessible(true);TargetEnum recursiveResult = (TargetEnum) method.invoke(this, targetParam, 2);result.add(recursiveResult.targetField);} catch (Exception e) {// No new checked exception}
// Recursion feature (protected, inner_class, recursion, this.methodName(arguments))// pos: property assignmentTargetEnum recursionProp = this.recursiveMethod(targetParam, 1); // 1st recursive callresult.add(recursionProp.targetField);recursionProp = this.recursiveMethod(targetParam, 0); // 2nd recursive call (method_feature:2)
return result;}
// Recursion helper method (method_feature: inner_class, recursion)protected TargetEnum recursiveMethod(TargetEnum target, int depth) {if (depth <= 0) {return target;}// this.methodName(arguments) - recursionreturn this.recursiveMethod(target, depth - 1);}}}}
// Permitted subclass for source enum's permits featurefinal enum SourceSubEnum implements SourceEnum {}
// Target class: enum, default modifier, has static nested class (target_feature)enum TargetEnum {TARGET1, TARGET2;
// Target field referenced by source (per_condition)String targetField = "target_field";
// Static nested class (target_feature)public static class TargetStaticNested {public static String staticField = "target_static";}
// Member inner class (target_inner: method's target position)public class TargetInnerClass {public TargetEnum getTargetInstance() {return TargetEnum.this;}}
// Target inner class instancepublic TargetInnerClass targetInnerClass = new TargetInnerClass();}
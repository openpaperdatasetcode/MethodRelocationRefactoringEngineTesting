package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {String value() default "";}
private class SourceClass {// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
@MethodAnnot("test_move_method")strictfp public TargetClass.TargetInner methodToMove(TargetClass target) {TargetClass.TargetInner inner = target.new TargetInner();// Variable call + contains target parameter (per_condition)target.toString();inner.toString();
// Access instance fieldString targetField = inner.innerField;// Expression statementinner.innerField = targetField.toUpperCase();
// For loop with inner_class overloading method callfor (int i = 0; i < 1; i++) {SourceInner innerHelper = new SourceInner(this);innerHelper.callOverload1(inner);innerHelper.callOverload2(inner, "suffix1");innerHelper.callOverload3(inner, "suffix2", 100);}
return inner;}
// Overload exists: additional instance methodpublic TargetClass.TargetInner methodToMove(TargetClass target, boolean flag) {TargetClass.TargetInner inner = methodToMove(target);inner.innerField = flag ? inner.innerField + "_flag" : inner.innerField;return inner;}
// Inner class for overloading featuresprivate class SourceInner {private SourceClass outerInstance;
public SourceInner(SourceClass outer) {this.outerInstance = outer;}
// Overloading method 1 (inner_class)private void callOverload1(TargetClass.TargetInner inner) {outerInstance.new InnerOperation().process(inner);}
// Overloading method 2 (inner_class)private void callOverload2(TargetClass.TargetInner inner, String suffix) {outerInstance.new InnerOperation().process(inner, suffix);}
// Overloading method 3 (inner_class)private void callOverload3(TargetClass.TargetInner inner, String suffix, int num) {outerInstance.new InnerOperation().process(inner, suffix, num);}}
// Inner class with overloading methodsprivate class InnerOperation {public void process(TargetClass.TargetInner inner) {inner.innerField += "_op1";}
public void process(TargetClass.TargetInner inner, String suffix) {inner.innerField += "op2" + suffix;}
public void process(TargetClass.TargetInner inner, String suffix, int num) {inner.innerField += "op3" + suffix + "_" + num;}}}
public class TargetClass {public class TargetInner {public String innerField = "targetInnerField"; // Source contains target's field (per_condition)}
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}}
class OthersClass {// Synchronized others_class methodpublic synchronized int callMethod(TargetClass target, MethodAnnot annot) {// Instance + (parameters) -> methodBody in annotation attribute valuesSourceClass source = new SourceClass();TargetClass.TargetInner inner = annot.value().equals("test_move_method")? ((TargetClass targetParam) -> source.methodToMove(targetParam)).apply(target): source.methodToMove(target);return inner.innerField.length();}}
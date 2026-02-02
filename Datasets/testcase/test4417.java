package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
public class Source {private Target sourceTargetField = new Target();private int outerField = 10;
static class StaticNested {int nestedStaticField;}
class Inner {class InnerRec {@MethodAnnotation@MethodAnnotationprivate Target instanceMethod() {Target newTarget = new Target();int recursionDepth = 1;
publicRecursionMethod(newTarget, recursionDepth);
int i = 0;do {variableCall(newTarget);expressionStatement(newTarget);i++;} while (i < 3);
newTarget.targetField = Source.this.outerField;return newTarget;}
public void publicRecursionMethod(Target target, int depth) {if (depth <= 0) {return;}target.createLocalInner().localMethod();publicRecursionMethod(target, depth - 1);}
private void variableCall(Target target) {int val = target.targetField;Source.this.sourceTargetField.targetField = val;}
private void expressionStatement(Target target) {target.targetField++;}}}}
class Target {int targetField;
Source.StaticNested createLocalInner() {class LocalInner {void localMethod() {Target.this.targetField *= 2;}}new LocalInner().localMethod();return new Source.StaticNested();}}
package same;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
@interface Source<T> {Target sourceTargetField = new Target() {};
static class SourceStaticNested<T> {public void nestedGenericMethod(T param) {}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceStaticNested<String>().nestedGenericMethod("anon_inner_val");}};
class SourceInner {@MethodAnnostrictfp void instanceMethod(T targetParam, Target.TargetMemberInner innerParam) {protected void subClassGenericInArray(U[] targets) {
if (targets == null || targets.length == 0) {
return;
}
targets[0].targetDefaultMethod();
}
Target[] targetArray = new Target[]{sourceTargetField};subClassGenericInArray(targetArray);
SourceStaticNested<T> rawNested = new SourceStaticNested();rawNested.nestedGenericMethod(targetParam);
Object var = sourceTargetField;innerParam.innerMethod("expr_val");String processedExpr = "processed_" + targetParam.toString();
return;}}}
protected @interface Target {class TargetMemberInner {public void innerMethod(String param) {}}
default void targetDefaultMethod() {}}
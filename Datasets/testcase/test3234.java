package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
// Annotation for call_method position@interface AnnoWithLambda {Class<? extends Consumer> value();}
// Others class for call_methodclass OtherClass {protected void normalMethod(String param) {}}
// Public source class (no features)public class SourceClass {// Call method implementation (Lambda in annotation attribute)@AnnoWithLambda(value = LambdaConsumer.class)static class LambdaConsumer implements Consumer<String> {@Overridepublic void accept(String s) {new OtherClass().normalMethod(s);}}
// Normal method (default access modifier, returns List<String>)List<String> normalMethod(AbstractTarget target) { // per_conditionList<String> result = new ArrayList<>();if (target == null) return result;
// PrefixExpression (numbers=1, modifier=protected)protected int count = 0;++count;
// While statementwhile (count < 3) {// Variable calltarget.targetMethod();AbstractTarget.TargetInner inner = target.new TargetInner();inner.innerMethod();result.add("loop-" + count);++count;}
// Used by reflectiontry {Method method = AbstractTarget.class.getMethod("targetMethod");method.invoke(target);} catch (Exception e) {// No new exception}
// Call method (others_class, protected, normal, Lambda, pos: annotation attribute)Consumer<String> lambda = s -> new OtherClass().normalMethod(s);lambda.accept("annotation-attr-value");
return result;}}
// Abstract target class (member inner class)abstract class AbstractTarget {public abstract void targetMethod();
// Member inner class (target_feature)public class TargetInner {public void innerMethod() {}}}
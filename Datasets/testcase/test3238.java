package sourcepkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.util.function.Function;import targetpkg.TargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {Class<? extends Function> value();}
// Generic source class (different package with target, no features)public class SourceClass<T> {// Call method implementation (Lambda in annotation attribute)@MethodAnno(value = LambdaFunction.class)static class LambdaFunction implements Function<String, Object> {@Overridepublic Object apply(String s) {return TargetSubClass.staticMethod(s);}}
// Varargs method (public access modifier, returns List<String>)@MethodAnno(value = LambdaFunction.class) // has_annotationpublic List<String> varargsMethod(TargetClass<T>... targets) { // per_conditionList<String> result = new ArrayList<>();if (targets == null) throw new NullPointerException("Targets cannot be null"); // NullPointerException
// For loop for static method featurefor (int i = 0; i < 2; i++) {// Static method feature (2, sub_class, static, super.methodName(), pos: for)int feat1 = TargetSubClass.staticFeatureMethod1(i);int feat2 = TargetSubClass.staticFeatureMethod2(i);result.add("feature-" + feat1 + "-" + feat2);}
for (TargetClass<T> target : targets) {// Assert statementassert target != null : "Target cannot be null";
// Switch statementswitch (target.getCode()) {case 1:target.targetMethod();break;case 2:TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();innerRec.recursiveAction();break;}
// Synchronized statementsynchronized (TargetClass.class) {target.targetMethod();}
// Expression statementtarget.setData((T) "test_data");
// Variable callT data = target.getData();result.add(data.toString());
// Used by reflectiontry {Method method = TargetClass.class.getMethod("targetMethod");method.invoke(target);} catch (Exception e) {// No new exception}
// Call method (sub_class, protected, static, Lambda, pos: annotation attribute)Function<String, Object> lambda = TargetSubClass::staticMethod;result.add(lambda.apply("anno-attr").toString());}
return result;}}
package targetpkg;
import java.util.List;
/**
Generic Target Class
Target feature: javadoc
@param <V> Generic type parameter*/protected class TargetClass<V> {private V data;private int code = 1;
public V getData() {return data;}
public void setData(V data) {this.data = data;}
public int getCode() {return code;}
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}}
// Target sub_class for method feature and call_methodprotected class TargetSubClass<V extends String> extends TargetClass<V> {// Static method feature 1public static int staticFeatureMethod1(int param) {super.toString(); // super.methodName()return param * 2;}
// Static method feature 2public static int staticFeatureMethod2(int param) {super.toString(); // super.methodName()return param * 3;}
// Call method static implementationprotected static Object staticMethod(String param) {return "sub-" + param;}}
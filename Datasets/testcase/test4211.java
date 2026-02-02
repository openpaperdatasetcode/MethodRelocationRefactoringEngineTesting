package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
public class SourceClass {// Static nested class (matches source_class.feature)public static class SourceStaticNested {public String staticNestedField;}
// Method with local inner class (matches source_class.feature)public void methodWithLocalInner() {class SourceLocalInner {void localInnerMethod() {}}new SourceLocalInner().localInnerMethod();}
// Refactored method: instance, return List<String>, final accesspublic final List<String> moveMethod(TargetClass targetParam) {List<String> resultList = new ArrayList<>();int count = 0;
// While statement (matches method.features)while (count < 3) {// EmptyStatement with ClassName.field and "1" (matches nested EmptyStatement feature)if (TargetClass.staticTargetField == 1) ; // Empty statement
// Variable call: access target's fieldString targetFieldVal = targetParam.targetField;resultList.add(targetFieldVal + "_" + count);
// Switch case (matches method.features)switch (count) {case 0:resultList.add("case_0");break;case 1:resultList.add("case_1");break;default:resultList.add("case_default");}
count++;}
// call_method: target type, protected, in Lambda expressions, uses outerInstance.new InnerClass().methodName()Supplier<Integer> lambdaSupplier = () -> new TargetClass().new TargetInner().callProtectedMethod();resultList.add("lambda_result:" + lambdaSupplier.get());
return resultList;}}
// Target class: default modifier, with anonymous inner class (target_feature)class TargetClass {// ClassName.field for EmptyStatement (matches nested feature)public static int staticTargetField = 1;// Target field for variable callString targetField = "targetData";
// Anonymous inner class (matches target_class.target_feature)Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target Anonymous Inner Class Running");}};
// Target inner class (for call_method's outerInstance.new InnerClass())class TargetInner {// call_method: protected, return int, uses constructor (implicit)protected int callProtectedMethod() {return targetField.length();}}}
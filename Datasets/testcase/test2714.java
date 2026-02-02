package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnno {String value() default ParentClass.DEFAULT;}
// Parent class for call_method (parent_class type)class ParentClass {public static final String DEFAULT = "parent";
// Protected generic method (call_method feature)protected <T> String genericMethod(T target) {return target.toString();}}
// Source: protected record class with member inner + static nested classprotected record SourceRecord() {// Member inner class (source_class feature)public class MemberInner extends ParentClass {// Public instance method (method_position: source_inner)public void instanceMethod(TargetRecord target) { // Contains target parameter (meets per_condition)// Array initialization (pos for overloading feature)TargetRecord[] targetArray = {target, new TargetRecord("two"), new TargetRecord("three")};
// Overloading methods: this.methodName(arguments)List<String> list1 = this.overloadMethod(targetArray[0]);List<String> list2 = this.overloadMethod(targetArray[0], "extra1");List<String> list3 = this.overloadMethod(targetArray[0], "extra1", "extra2");
// 3 protected LambdaExpressionprotected Runnable lambda1 = () -> variableCall(targetArray[0]);protected Runnable lambda2 = () -> System.out.println(list1);protected Runnable lambda3 = () -> this.overloadMethod(targetArray[1]);
// Expression statementlambda1.run();lambda2.run();lambda3.run();
// Annotation attribute with call_method (pos)@CallAnnoString annoVal = this.genericMethod(target); // instanceReference.methodName(arguments)}
// 3 overloading methods (method_feature: overloading)public List<String> overloadMethod(TargetRecord target) {return new ArrayList<>(List.of(target.value()));}public List<String> overloadMethod(TargetRecord target, String extra) {return new ArrayList<>(List.of(target.value(), extra));}public List<String> overloadMethod(TargetRecord target, String extra1, String extra2) {return new ArrayList<>(List.of(target.value(), extra1, extra2));}
private void variableCall(TargetRecord target) {TargetRecord local = target;System.out.println(local.value());}}
// Static nested class (source_class feature)public static class StaticNested {}}
// Target: protected record class (no extra target_feature)protected record TargetRecord(String value) {}
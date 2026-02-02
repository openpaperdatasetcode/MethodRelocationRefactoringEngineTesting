package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
// Interface for override violationinterface OverrideInterface {List<String> methodToRefactor(TargetEnum target);}
// Source enum: default modifier, with two member inner classesenum SourceEnum implements OverrideInterface {INSTANCE;
// 1st member inner class (source feature)class FirstInner {<T extends Number> void boundedMethod(T value) {}}
// 2nd member inner class (source feature)class SecondInner {}
// Overloading method 1 (method feature)private void overloadedMethod(TargetEnum target) {}
// Overloading method 2 (method feature)private void overloadedMethod(TargetEnum target, int num) {}
// Static code block (call_method pos)static {TargetEnum target = TargetEnum.VALUE;TargetEnum result = target.protectedMethod("init"); // call_method feature}
// Method to test: normal type, List<String> return, default access@Overridepublic List<String> methodToRefactor(TargetEnum target) { // override violation (return type mismatch)List<String> list = new ArrayList<>();FirstInner inner = new FirstInner();inner.boundedMethod(1); // with_bounds feature
// do-while loop (method feature pos)int count = 0;do {// Lambda expression (method_feature "(parameters) -> methodBody")Function<TargetEnum, Integer> func = t -> t.innerField;if (func.apply(target) == 1) {overloadedMethod(target); // overloading feature}count++;} while (count < 2);
variableCall(); // variable call featurereturn list;}
private void variableCall() {}}
// Diff-package class for LabeledStatement pospackage test.other;import test.SourceEnum;import test.TargetEnum;import java.util.List;
public class DiffPackageClass {public void useSourceMethod() {TargetEnum target = TargetEnum.VALUE;List<String> result = SourceEnum.INSTANCE.methodToRefactor(target);
// LabeledStatement with target_feature (this.field & 2)label:if (target.innerField == 2) {break label;}}}
// Target enum: private modifier, with member inner classprivate enum TargetEnum {VALUE;
// Member inner class (target feature)class TargetInner {}
int innerField; // this.field for target_feature
// call_method: target type, protected modifier, normal typeprotected TargetEnum protectedMethod(String arg) {return VALUE;}}
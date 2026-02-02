import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;
// Target class with type parameter and static nested class (private modifier)private class TargetClass<T> {// Static nested class for target_inner_recstatic class TargetNestedClass {private int nestedField = 1;
protected int getNestedField() {return nestedField;}}
// Super method for SuperMethodInvocationprotected int superMethod() {return 1;}}
// Others class for call_method (overloading + super.methodName)class OthersClass extends TargetClass<String> {// Overloading method 1Object callMethod(TargetClass.TargetNestedClass param) {return super.superMethod();}
// Overloading method 2 (overload feature)Object callMethod(TargetClass.TargetNestedClass param, int num) {return param.getNestedField();}}
// Source class (public, static nested classes x2, same package)public class SourceClass {// Static nested class 1static class SourceNestedClass1 {int value = 1;}
// Static nested class 2static class SourceNestedClass2 {int getValue() {return 1;}}
// Method to be refactored (instance, base type return, protected, source position)protected int moveMethod(TargetClass.TargetNestedClass targetParam) {// Enhanced for statementList<Integer> numList = Arrays.asList(1, 2, 3);int sum = 0;for (int num : numList) {sum += num;
// Call_method in for loop (others_class, overloading, super.methodName)OthersClass others = new OthersClass();others.callMethod(targetParam);others.callMethod(targetParam, 1);}
// SuperMethodInvocation with number 1 (modifier protected)int superVal = new TargetClass<String>() {}.superMethod();
// Variable callSourceNestedClass1 nested1 = new SourceNestedClass1();int varCallVal = nested1.value + new SourceNestedClass2().getValue();
// Used by reflectiontry {Method reflectMethod = SourceClass.class.getDeclaredMethod("moveMethod", TargetClass.TargetNestedClass.class);reflectMethod.setAccessible(true);reflectMethod.invoke(this, targetParam);} catch (Exception e) {e.printStackTrace();}
// No new exception (no throw/new Exception)return sum + superVal + varCallVal + targetParam.getNestedField();}}
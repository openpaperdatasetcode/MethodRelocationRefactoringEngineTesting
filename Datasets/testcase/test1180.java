package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Generic source class with required features
@param <T> type parameter (generic class feature)*/public class SourceClass<T> {// Source feature: member inner classclass SourceMemberInnerClass {}
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Instance initializer block (instance code blocks){// Call method: source type, protected modifier, pos in instance code blocksprotected Object callSourceOverriddenMethod(SubSourceClass<String> subSource, TargetClass<Integer> target) {// Overriding feature + super.methodName()return subSource.overriddenMethod(target);}}
/**
Instance method to be refactored (default access, return List<String>)
@param targetParam target class parameter (per_condition)
@return List<String> result (method types parameter is:none)*/List<String> refactorMethod(TargetClass<T> targetParam) {List<String> result = new ArrayList<>();
// Constructor invocation (target class constructor)TargetClass<T> newTarget = new TargetClass<>();
// Variable callTargetClass<T> tempTarget = targetParam;result.add(tempTarget.getLocalInnerData());
// For statementfor (int i = 0; i < 3; i++) {if (i == 1) {break; // Break statement}if (i == 0) {continue; // Continue statement}result.add("loop_" + i);}
// No new exception thrownreturn result;}
/**
Overloaded method (overload_exist feature)
@param targetParam target class parameter
@param extraArg additional parameter for overloading
@return List<String> result
*/
List<String> refactorMethod(TargetClass<T> targetParam, String extraArg) {
List<String> result = refactorMethod(targetParam);
result.add(extraArg);
return result;
}
}
/**
Subclass for call_method overriding feature
@param type parameter
*/
class SubSourceClass extends SourceClass {
// Overriding parent class method (target_feature: overriding)
@Override
List<String> refactorMethod(TargetClass targetParam) {
super.refactorMethod(targetParam); // super.methodName()
return new ArrayList<>(List.of("overridden_result"));
}
}
/**
Generic target class (default modifier, target_feature: local inner class)
@param <V> type parameter
*/
class TargetClass<V> {
// Target feature: local inner class
public String getLocalInnerData() {
class TargetLocalInnerClass {
String innerData = "local_inner_data";
String getData() {
return innerData;
}
}
TargetLocalInnerClass localInner = new TargetLocalInnerClass();
return localInner.getData();
}
}

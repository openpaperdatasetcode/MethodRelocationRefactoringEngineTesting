package test.refactor.movemethod;
import java.lang.reflect.Method;
/**
Javadoc for target class (target_feature: javadoc)
@param <T> target generic type*/private class TargetClass<T> {// Target_feature: static nested classpublic static class TargetStaticNested {public String processData(String input) {return "Processed: " + input;}}
public T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
public T getTargetField() {return targetField;}}
// Source generic class (private, same package, type parameter + static nested + member inner class)private class SourceClass<S, T> extends BaseClass<T> {// Feature: type parameter (S, T)private S sourceField;
// Feature: static nested classpublic static class SourceStaticNested {public static U convert(Object obj) {
return (U) obj;
}
}
// Feature: member inner classpublic class MemberInnerClass {public String getInnerData(T target) {return target.toString() + "_inner";}}
public SourceClass(S sourceField) {this.sourceField = sourceField;}
// Method to be refactored: overriding, protected, Object return, contains target parameter (per_condition)@Overrideprotected Object processTarget(TargetClass<T> targetParam) {// Feature: type declaration statementTargetClass.TargetStaticNested nestedInstance;MemberInnerClass innerInstance;
try {// Feature: depends_on_inner_classinnerInstance = new MemberInnerClass();String innerData = innerInstance.getInnerData(targetParam.getTargetField());
// Feature: variable call + access_instance_methodnestedInstance = new TargetClass.TargetStaticNested();String nestedData = nestedInstance.processData(innerData);
// Feature: overload_exist (call overloaded method)Object overloadedResult = processTarget(targetParam, sourceField);
// Feature: used_by_reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("getTargetField");Object reflectedResult = targetMethod.invoke(targetParam);
// Feature: static nested class usageT converted = SourceStaticNested.convert(reflectedResult);return nestedData + "" + overloadedResult + "" + converted;} catch (ReflectiveOperationException e) {// Feature: no_new_exception (rethrow without wrapping)throw new RuntimeException(e);}}
// Feature: overload_exist (overloaded method)protected Object processTarget(TargetClass<T> targetParam, S sourceParam) {return targetParam.getTargetField() + "_" + sourceParam;}}
// Base class for overriding featureabstract class BaseClass<T> {protected abstract Object processTarget(TargetClass<T> targetParam);}
// Test classpublic class MoveMethodTest5240 {public static void main(String[] args) {TargetClass<String> target = new TargetClass<>("testTarget");SourceClass<Integer, String> source = new SourceClass<>(123);Object result = source.processTarget(target);System.out.println(result);}}
import java.lang.reflect.Method;import java.util.function.Function;
// Base class for source generic class extensionclass BaseSourceClass {}
// Base class for target generic class extensionclass BaseTargetClass {}
// Different package helper class (simulated via same package with distinct naming for diff_package_others)class DiffPackageHelper {public int superField = 1;}
// Functional interface for recursion position@FunctionalInterfaceinterface RecursiveFunction<T, R> {R apply(T t);}
// Source class: generic, private modifier, same package, type parameter, extends, two static nested classesprivate class SourceClass<T extends BaseSourceClass> {// Static nested class 1static class StaticNestedClass1 {private String data;}
// Static nested class 2static class StaticNestedClass2 {private int count;}
// Inner class containing the method to move (method_position: source_inner)class SourceInnerClass {// Method to refactor: instance, base type return, default access, features as requiredint methodToMove(TargetClass<String> targetParam) { // per_condition: contains target parameter// VariableDeclarationStatement: private modifier, diff_package_others pos, super.field + 1private DiffPackageHelper helper = new DiffPackageHelper();int localVar = helper.superField; // super.field (DiffPackageHelper's field) with value 1
// Recursion feature: public modifier, functional interfaces pos, Object return, instanceReference callRecursiveFunction<Integer, Object> recursiveFunc = new RecursiveFunction<>() {@Overridepublic Object apply(Integer num) {if (num <= 0) return "end";// Recursion: instanceReference.methodName(arguments)return this.apply(num - 1); // 3: num=3 in call below}};Object recursionResult = recursiveFunc.apply(3);
// Assert statementassert localVar == 1 : "Super field value mismatch";
// Expression statementtargetParam.value = 100;
// Variable calltargetParam.processLocalInner();
// Override violation: method signature matches target but return type incompatible (target returns long)// Used by reflectiontry {Method method = SourceInnerClass.class.getMethod("methodToMove", TargetClass.class);method.invoke(this, targetParam);} catch (Exception e) {// No new exception (no_new_exception feature)}
// Return base type (int)return localVar;}}}
// Target class: generic, public modifier, extends, local inner class (target_feature)public class TargetClass {
public int value;
// Method with same signature but different return type (override violation)long methodToMove(TargetClass<String> targetParam) {return 0L;}
// Method containing local inner class (target_feature: local inner class)public void processLocalInner() {// Local inner classclass LocalInnerClass {private void print() {System.out.println(value);}}new LocalInnerClass().print();}}
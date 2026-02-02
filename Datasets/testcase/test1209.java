package test.refactoring.movemethod;
/**
Sealed generic source class with member inner class and static nested class features
@param <S> type parameter (generic class feature)
@permits Permitted subclasses of the sealed source class*/sealed class SourceClass<S> permits SourceSubClass1, SourceSubClass2 {// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner classpublic class SourceMemberInnerClass {public void innerMethod() {}}
/**
Instance method to be refactored (protected access, returns Object)
@param targetParam target class parameter (per_condition)
@return Object result*/@RefactorAnnotation // has_annotation featureprotected Object refactorTargetMethod(TargetClass<String> targetParam) {// Variable callTargetClass<String> tempTarget = targetParam;TargetClass<String>.TargetMemberInner inner = tempTarget.new TargetMemberInner();inner.innerMethod();
// Assert statementassert tempTarget.getData() != null : "Target data cannot be null";
// The parameter list of constructors (pos for overriding nested method)NestedConstructorHelper helper = new NestedConstructorHelper(() -> overridingNestedMethod(tempTarget, 2) // Overriding method as constructor parameter);
// No new exception thrownreturn tempTarget;}
/**
Overriding nested method (type: overriding, modifier: private, return_type: TargetClass Type)
@param target target class instance
@param num "2" in method_feature
@return TargetClass instance
*/
private TargetClass<String> overridingNestedMethod(TargetClass<String> target, int num) {
int count = 2; // "2" in method_feature
this.refactorTargetMethod(target); // this.methodName(arguments)
return target; // "target" + "overriding" features
}
// Custom annotation for has_annotation feature@interface RefactorAnnotation {}
// Helper class for constructor parameter list featurestatic class NestedConstructorHelper {public NestedConstructorHelper(Runnable methodSupplier) {methodSupplier.run();}}}
// Permitted subclasses of sealed SourceClass (sealed class requirement)final class SourceSubClass1<S> extends SourceClass<S> {}final class SourceSubClass2<S> extends SourceClass<S> {}
/**
Generic target class: public modifier, target_feature: member inner class (target_inner)
@param <T> type parameter (generic class feature)*/public class TargetClass<T> {private T data;
public TargetClass(T data) {this.data = data;}
// Target feature: member inner class (target_inner)public class TargetMemberInner {public void innerMethod() {System.out.println("Target inner class method: " + data);}}
// Getter for data (used in assert statement)public T getData() {return data;}}
// Test class to verify functionalityclass SourceClassTest {public static void main(String[] args) {SourceClass<String> source = new SourceSubClass1<>();TargetClass<String> target = new TargetClass<>("test_data");
Object result = source.refactorTargetMethod(target);System.out.println("Refactor result: " + result);}}
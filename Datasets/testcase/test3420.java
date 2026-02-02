package test;
import otherpkg.OtherPackageParent;
abstract class SourceClass {private String outerPrivateField = "private_data";
// Static nested class (source feature)public static class SourceStaticNested {}
/**
Method Javadoc: Instance method for Move Method refactoring test
Contains all required features and depends on target class
@param targetParam Target class parameter (per condition)
@return Object result of method execution*/Object instanceMethod(TargetClass<String> targetParam) {// Access outer private fieldString privateVal = this.outerPrivateField;
// Constructor invocation: target class with type parameterTargetClass<Integer> boundedTarget = new TargetClass<>();
// With bounds: target's type parameter with upper boundTargetClass<? extends Number> boundedInstance = boundedTarget;
// VariableDeclarationStatement (private, target_feature: super.field x1, pos: diff_package_others)OtherPackageSub other = new OtherPackageSub();private String superFieldVal = other.superField;
// Public TypeMethodReference (numbers=1)public java.util.function.Function<String, Integer> typeRef = Integer::valueOf;
// This.methodName(arguments) + variable callthis.helperMethod(privateVal);targetParam.variableCall(privateVal);boundedInstance.process(100);
return boundedTarget;}
private void helperMethod(String data) {}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void innerMethod() {}}new SourceLocalInner().innerMethod();}}
// Target class (default modifier, with type parameter)class TargetClass<T> {public void variableCall(String data) {}
public void process(U value) {}
}
// Diff package classes (separate package)package otherpkg;
public class OtherPackageParent {protected String superField = "parent_field";}
public class OtherPackageSub extends OtherPackageParent {}
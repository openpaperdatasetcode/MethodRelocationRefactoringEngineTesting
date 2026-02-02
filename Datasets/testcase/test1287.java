package test.refactor.movemethod;
import java.io.IOException;
// Target generic class (private, with local inner class)private class TargetClass<T> {private T targetField;
public TargetClass(T targetField) {this.targetField = targetField;}
public void targetMethod() {// Local inner class (target_feature)class TargetLocalInnerClass {void process(T data) {System.out.println("Local inner class in TargetClass: " + data);}}TargetLocalInnerClass inner = new TargetLocalInnerClass();inner.process(targetField);}
public T getTargetField() {return targetField;}}
// Parent interface for source class implementationinterface Processable<T> {void process(T data);}
// Source generic class (protected, same package, implements interface, two static nested classes)protected class SourceClass<S, T> implements Processable<S> {protected S outerField;
public SourceClass(S outerField) {this.outerField = outerField;}
// First static nested classpublic static class FirstStaticNestedClass {
private U nestedField;
public FirstStaticNestedClass(U nestedField) {this.nestedField = nestedField;}
public U getNestedField() {return nestedField;}}
// Second static nested classprotected static class SecondStaticNestedClass<V> {// Call method: inner_class type, protected modifier, static, ClassName::methodName, pos: constructor parameter listprotected static void callMethod(FirstStaticNestedClass<?> nested, Runnable action) {action.run();System.out.println("Call method with static nested class: " + nested.getNestedField());}
public SecondStaticNestedClass(FirstStaticNestedClass<V> nested) {// Call method in constructor parameter list (pos requirement)SecondStaticNestedClass::callMethod;callMethod(nested, () -> System.out.println("Constructor parameter list call"));}}
// Instance method to be refactored (final, return TargetClass Type, method_position: source, target class: target_inner_rec)public final TargetClass<T> methodToRefactor(TargetClass<T> targetParam) throws IOException { // Contains target parameter (per_condition), requires_throws// OuterClass.this.xS outerRef = SourceClass.this.outerField;System.out.println("Outer class field: " + outerRef);
// Variable callT targetVar = targetParam.getTargetField();System.out.println("Target variable call: " + targetVar);
// Raw type usageFirstStaticNestedClass rawNested = new FirstStaticNestedClass("rawTypeData"); // raw_typenew SecondStaticNestedClass(rawNested);
// Return TargetClass Typereturn new TargetClass<>(targetVar);}
@Overridepublic void process(S data) {System.out.println("Process interface method: " + data);}}
// Test classpublic class MoveMethodRefactorTest_5201 {public static void main(String[] args) throws IOException {SourceClass<String, Integer> source = new SourceClass<>("outerValue");TargetClass<Integer> target = new TargetClass<>(100);TargetClass<Integer> result = source.methodToRefactor(target);
SourceClass.FirstStaticNestedClass<String> nested = new SourceClass.FirstStaticNestedClass<>("nestedData");new SourceClass.SecondStaticNestedClass<>(nested);}}
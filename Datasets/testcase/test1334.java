package test.refactor.movemethod;
import java.util.function.Function;
// Source generic class (private modifier, same package, member inner + static nested class)private class SourceClass<S> {// Feature: static nested classpublic static class SourceStaticNested<T> {public static U staticGenericMethod(U value) {
return value;
}
}
// Feature: member inner class (source_inner - method position)protected class SourceInner {/**
Method Javadoc (method javadoc feature)
@param targetParam Target class parameter (per_condition)
@param <T> Generic type parameter (method types parameter is:generic)
@return TargetClass instance (TargetClass Type return)*/protected <T extends CharSequence> TargetClass<T> processTarget(TargetClass<T> targetParam) {// Per_condition: contains target parameterif (targetParam == null) {throw new NullPointerException("Target cannot be null"); // NullPointerException feature}
// With_bounds (generic with bounds)Function<? extends T, String> boundedFunc = CharSequence::toString;
// Variable call: target's local inner class (target_feature)targetParam.useLocalInner();
try {// Variable call: static nested class generic methodInteger staticResult = SourceStaticNested.staticGenericMethod(100);System.out.println("Static generic result: " + staticResult);
// Variable call: target's generic field and methodT targetData = targetParam.getTargetData();String processed = boundedFunc.apply(targetData);targetParam.updateTargetData((T) (processed + "_updated"));} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam; // TargetClass Type return}}
// Public method to invoke inner class methodpublic <T extends CharSequence> TargetClass<T> invokeProcess(TargetClass<T> target) {SourceInner inner = new SourceInner();return inner.processTarget(target);}}
// Target generic class (default modifier, target_feature: local inner class)class TargetClass<T> {private T targetData;
public TargetClass(T targetData) {this.targetData = targetData;}
public T getTargetData() {return targetData;}
public void updateTargetData(T data) {this.targetData = data;}
// Target_feature: local inner classpublic void useLocalInner() {class TargetLocalInner {public void printData() {System.out.println("Target local inner: " + targetData);}}new TargetLocalInner().printData();}}
// Test classpublic class MoveMethodTest5286 {public static void main(String[] args) {SourceClass<Double> source = new SourceClass<>();TargetClass<String> target = new TargetClass<>("testGenericData");TargetClass<String> result = source.invokeProcess(target);System.out.println("Refactored target data: " + result.getTargetData());}}
package test;
import java.util.function.Consumer;
@MyAnnotationprotected class SourceClass<T> {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {class SourceInnerRec {public void methodToMove(TargetClass<T> targetParam) {// Normal method in ternary operatorint val = (1 > 0) ? SourceClass.staticMethod(1) : 0;
// Variable callTargetClass<T>.TargetInner inner = targetParam.new TargetInner();T var = inner.value;
// Raw typeTargetClass rawTarget = new TargetClass();
// Depends on inner classSourceStaticNested1 nested1 = new SourceStaticNested1();dependsOnInnerClass(nested1);
// Call method in functional interfaceOthersClass.call((Consumer<T>) t -> outerInstance.new SourceInner().new SourceInnerRec().process(t));}
private void dependsOnInnerClass(SourceStaticNested1 ssn) {}private void process(T t) {}
static int staticMethod(int num) {return num;}}}
SourceClass<T> outerInstance = this;}
class TargetClass {
class TargetInner {
U value;
}
}
class OthersClass {private static <T> void call(Consumer<T> consumer) {}}
@interface MyAnnotation {}

package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
private class SourceClass<T> extends ParentClass {private TargetClass<String> targetField = new TargetClass<>() {};
{new Object() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}
class SourceInner {@MyAnnotpublic abstract Object methodToMove() throws Exception;
public TargetClass<T>.TargetInner instanceMethod() {// Instance method in do-whileTargetClass<T>.TargetInner inner;do {inner = (TargetClass<T>.TargetInner) super.superMethod(1);} while (inner == null);
// Constructor invocationTargetClass<T>.TargetInner newInner = targetField.new TargetInner();
// Variable callObject var = inner.field;
// Overload existsoverloadedMethod(inner);overloadedMethod("string");
return inner;}
private void overloadedMethod(TargetClass<T>.TargetInner param) {}private void overloadedMethod(String str) {}}}
abstract class TargetClass {
class TargetInner {
U field;
}
}
class ParentClass {protected Object superMethod(int num) {return null;}}
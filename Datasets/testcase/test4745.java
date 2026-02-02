package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
protected class SourceClass {class MemberInner {}
@MyAnnotationprivate Object moveMethod(TargetClass.Inner targetInner) {class LocalInner {}LocalInner li = new LocalInner();
targetInner.overload(1);targetInner.overload("str");variableCall(targetInner);
switch (1) {case 1:Supplier<Object> supplier = TargetClass.StaticNestedNested::staticMethod;supplier.get();break;}
return targetInner.field;}
private void variableCall(TargetClass.Inner inner) {}}
private class TargetClass {static class StaticNested {protected static Object staticMethod() {return null;}}
class Inner {Object field;
void overload(int i) {}void overload(String s) {}}}
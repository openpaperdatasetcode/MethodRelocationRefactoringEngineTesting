package test;
import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value() default "";}
public class SourceClass {private ProtectedTarget targetField;private int outerPrivateField = 3;
public class SourceInner {public ProtectedTarget overloadedMethod() {return new ProtectedTarget();}
public ProtectedTarget overloadedMethod(int param) {try {Method method = getClass().getMethod("overloadedMethod");method.invoke(this);} catch (Exception e) {}
synchronized (this) {targetField.nestedStatic.field = outerPrivateField;return targetField;}}}
static class SourceStaticNested {}
@CallAnnotation(value = callMethod().toUpperCase())private String annotationField;
default String callMethod() {return new SourceInner().overloadedMethod().nestedStatic.m1().m2().m3();}}
protected class ProtectedTarget {static class NestedStatic {int field;
NestedStatic m1() { return this; }NestedStatic m2() { return this; }NestedStatic m3() { return this; }}NestedStatic nestedStatic = new NestedStatic();}
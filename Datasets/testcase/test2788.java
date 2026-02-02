package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public class SourceClass<T extends Number & Comparable<T>> {private String outerPrivateField = "privateField";
@TestAnnotationprotected static Object methodToMove(TargetClass target) {
// Functional interface with overriding method
Runnable functionalInterface = () -> {
int baseType = target.overrideMethod(1);
};
functionalInterface.run();
// Expression statementtarget.toString();// Variable callString varCall = target.getField();// Access outer private fieldString privateAccess = outerPrivateField;// Uses outer this (for static method, refers to SourceClass class context)Class<?> outerClass = SourceClass.class;
return varCall;}
public void createLocalInners() {class LocalInner1 {}class LocalInner2 {}}}
protected class TargetClass<V> {private V targetField;
public V getField() {return targetField;}
public int overrideMethod(int arg) {return super.hashCode() + arg;}}
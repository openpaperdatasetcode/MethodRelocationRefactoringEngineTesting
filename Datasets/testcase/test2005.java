package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
interface TestInterface {}
protected class SourceClass implements TestInterface {protected int outerProtectedField = 42;private TargetClass targetField;
public SourceClass() {this.targetField = new TargetClass();}
class MemberInner {void useOuter() {System.out.println(outerProtectedField);}}
static class StaticNested {void doSomething() {}}
@CustomAnnotationprivate void methodToMove() {TargetClass newTarget = new TargetClass();SuperClass superObj = new SuperClass(10);targetField.accessTargetField();System.out.println(outerProtectedField);}}
class SuperClass {public SuperClass(int value) {}}
/**
Target class with static nested class*/private class TargetClass {private int targetField;
void accessTargetField() {System.out.println(targetField);}
static class TargetStaticNested {void nestedMethod() {}}}
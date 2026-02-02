package test;
import java.lang.reflect.Field;
protected class SourceClass {private int outerField;
class MemberInner1 {class MemberInner2 {/**
Instance method with reflection and overload features*/private Object instanceMethod(TargetClass target) {// Access target fieldString fieldVal = target.targetField;
// Constructor invocationTargetClass newTarget = new TargetClass();
// OuterClass.this.x accessint outerVal = SourceClass.this.outerField;
variableCall();
// Overloaded methods existoverloadedMethod();overloadedMethod(1);overloadedMethod("str");
// Used by reflectiontry {Field field = TargetClass.class.getField("targetField");} catch (NoSuchFieldException e) {}
return new Object();}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}private void overloadedMethod(String str) {}}}}
private class TargetClass {String targetField;}
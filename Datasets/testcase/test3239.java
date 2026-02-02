package test;
import java.util.List;
// Protected source class (member inner class + member inner class)protected class SourceClass {// First member inner class (source feature)public class FirstInner {}
// Second member inner class (source feature)public class SecondInner {}
// Varargs method (final access modifier, returns Object, with_bounds)public final <T extends CharSequence> Object varargsMethod(TargetClass<T>... targets) { // per_condition + with_boundsif (targets == null || targets.length == 0) return null;
// Type declaration statementT typeDeclared = (T) "declared_value";
// ConstructorInvocation (private, target_feature: this.field x2, pos: same_package_others)private TargetClass<T> target1 = new TargetClass<>(typeDeclared);private TargetClass<T> target2 = new TargetClass<>(typeDeclared);int field1 = target1.targetField.length();int field2 = target2.anotherField.length();
// Super keywords (reference to outer class)Object superRef = SourceClass.super.toString();
for (TargetClass<T> target : targets) {// Synchronized statementsynchronized (target) {target.targetMethod();}
// Expression statementtarget.setData((T) "updated_data");
// Variable callT data = target.getData();TargetClass.TargetStaticNested.staticMethod();}
// return this;return this;}}
// Target class (default modifier, static nested class)class TargetClass<T extends CharSequence> {public T targetField;public T anotherField; // this.field for ConstructorInvocation
public TargetClass(T data) {this.targetField = data;this.anotherField = data;}
public T getData() {return targetField;}
public void setData(T data) {this.targetField = data;}
public void targetMethod() {}
// Static nested class (target_feature)public static class TargetStaticNested {public static void staticMethod() {}}}
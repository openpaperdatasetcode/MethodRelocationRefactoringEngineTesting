package test.pkg;
import java.lang.reflect.Type;import java.util.List;
interface SourceInterface {// Static nested class (source_class feature)static class StaticNestedClass {protected String outerProtectedField = "sourceProtected";
public Object recursiveHelper(int depth) {return depth <= 0 ? "base" : new StaticNestedClass().recursiveHelper(depth - 1);}}
// Member inner class (source_inner_rec: method in source's inner class)class SourceInnerClass {@Deprecated // has_annotation featureprotected int process(TargetInterface<String> targetParam) throws IllegalAccessException {// Type declaration statementTypeLiteral<String> typeLiteral = new TypeLiteral<String>() {}; // numbers=2 (TypeLiteral instance)Type declaredType = typeLiteral.getType();
// Access outer protected field (access_outer_protected)String outerField = new StaticNestedClass().outerProtectedField;
// Throw statementif (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}
// Recursion feature (pos: expression, 1: depth, others_class: StaticNestedClass)Object recursiveResult = new StaticNestedClass().recursiveHelper(1);if (recursiveResult == null) {throw new IllegalAccessException("Recursion failed");}
// Variable call: target's member inner class methodTargetInterface.MemberInnerClass targetInner = targetParam.new MemberInnerClass();targetInner.innerMethod(outerField);
// Super constructor invocation (implicit in inner class, explicit via target's inner class)TargetInterface.MemberInnerClass superInvokedInner = targetParam.new MemberInnerClass() {};
return declaredType.getTypeName().length() + outerField.length();}}
// Local inner class (source_class feature)default void interfaceMethod() {class LocalInnerClass {public void localMethod() {new SourceInnerClass().process(null);}}new LocalInnerClass().localMethod();}}
public interface TargetInterface<T> extends TestSuperInterface {// Type parameter (target_feature)T getTargetData();
// Member inner class (target_feature)class MemberInnerClass {public void innerMethod(String data) {}}
// Method will be moved here (adjust access modifier to public for interface compatibility):// @Deprecated// public int process(TargetInterface<String> targetParam) throws IllegalAccessException { ... }}
// Super interface for TargetInterface (implements feature)interface TestSuperInterface {default void superInterfaceMethod() {}}
// TypeLiteral class (exp: TypeLiteral feature)abstract class TypeLiteral<T> {public Type getType() {return getClass().getGenericSuperclass();}}
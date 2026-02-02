package test;
import targetpackage.TargetClass;import java.util.Arrays;import java.util.function.Consumer;
class SourceClass<T extends ParentClass> {private TargetClass<String> targetField = new TargetClass<>();
class InnerClass1 {}class InnerClass2 {}
@MyAnnotationpublic final Object normalMethod() {// VariableDeclarationStatement: diff_package_target + ClassName.field (1)targetpackage.TargetClass.StaticField staticField = targetpackage.TargetClass.StaticField.INSTANCE;
// Varargs method (parent_class + 3 + ClassName::methodName + ternary)String[] args1 = {"a"}, args2 = {"b"}, args3 = {"c"};Consumer<String[]> processor = ParentClass::varargsMethod;processor.accept(args1.length > 0 ? args1 : args2);processor.accept(args2.length > 0 ? args2 : args3);processor.accept(args3.length > 0 ? args3 : args1);
// Switch statementTargetClass.MemberInner inner = targetField.new MemberInner();switch (inner.getType()) {case 1:inner.doAction();break;default:targetField.doSomething();}
return staticField;}}
// Different package: targetpackagepackage targetpackage;
class TargetClass<V> {public static class StaticField {public static final StaticField INSTANCE = new StaticField();}
class MemberInner {int getType() { return 1; }void doAction() {}}
void doSomething() {}}
// Same package as SourceClasspackage test;
class ParentClass {public static void varargsMethod(String... params) {Arrays.stream(params).forEach(System.out::println);}}
@interface MyAnnotation {}
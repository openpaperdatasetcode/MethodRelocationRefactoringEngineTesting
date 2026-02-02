package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
public abstract class SourceClass<T> {protected String sourceProtected;private TargetClass<Integer> targetInstance = new TargetClass<>();
public SourceClass() {}
public abstract void instanceMethod();
@TestAnnotationpublic static Object methodToMove() {int base = 1; // 1 (base type)SourceClass<?> anon1 = new SourceClass<Object>() { // anonymous inner class@Overridepublic void instanceMethod() {}};SourceClass<?> anon2 = new SourceClass<String>() { // another anonymous inner class@Overridepublic void instanceMethod() {}};
class LocalType {} // type declaration statementLocalType lt = new LocalType();
for (int i = 0; i < 5; i++) {} // for statement
Object obj = targetInstance.targetField; // source contains target's field (per_condition)anon1.instanceMethod(); // access_instance_methodString val = sourceProtected; // access_outer_protected
switch (base) {case 1:TargetClass.NestedStatic ns = new TargetClass.NestedStatic(); // inner_class (static)int res = ns.staticMethod(); // static method callreturn super.toString(); // super.methodName()default:return null;}}}
abstract class TargetClass {
Object targetField;
public TargetClass() {}
class LocalInner {public LocalInner() {super(); // super constructor invocation}}
static class NestedStatic {int staticMethod() { return 0; } // default modifier (static method)}}
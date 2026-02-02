package test.refactor.movemethod;
import java.lang.reflect.Method;
// Parent class for super constructor invocation and super keywordsabstract class ParentBaseClass {protected String parentProtectedField = "ParentProtectedValue";
public ParentBaseClass(String param) {}
protected abstract void parentMethod();}
// Source abstract class (public, same package, local inner + static nested class)public abstract class SourceClass extends ParentBaseClass {// Protected field for access_outer_protectedprotected String outerProtectedField = "SourceProtectedValue";
// Feature: static nested classpublic static class SourceStaticNested {}
// Super constructor invocationpublic SourceClass() {super("sourceSuperParam");}
// Member inner class (source_inner_rec: recursive inner structure)public class SourceInner {public class RecursiveInner {// Method to be refactored: varargs, protected, void return, in source_inner_recpublic void processTarget(TargetClass targetParam, String... args) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// Super keywordssuper.toString();String parentValue = SourceClass.super.parentProtectedField;
// Access_outer_protectedString outerValue = SourceClass.this.outerProtectedField;
// Public FieldAccess (numbers:3, modifier:public, exp:FieldAccess)String field1 = targetParam.targetField1; // 1st FieldAccessString field2 = targetParam.getInner().innerField; // 2nd FieldAccessString field3 = targetParam.getInner().getRecursiveInner().recursiveField; // 3rd FieldAccess
// Local inner class (source feature)class LocalInner {public void useReflection() throws ReflectiveOperationException {// Used_by_reflectionMethod targetMethod = TargetClass.class.getDeclaredMethod("getInner");targetMethod.invoke(targetParam);}}
try {LocalInner local = new LocalInner();local.useReflection();
// Variable call: target's static nested classTargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.process(field1);
// Variable call: target_inner_rec usageTargetClass.TargetInner targetInner = targetParam.getInner();TargetClass.TargetInner.RecursiveTargetInner recursiveInner = targetInner.getRecursiveInner();recursiveInner.useFields(field2, field3);
// Exception handling statements with call_method (overriding, super.methodName())try {SourceClass.this.callInExceptionHandling(targetParam);} catch (RuntimeException e) {// Call_method pos: exception handling statementsSourceClass.this.callInExceptionHandling(targetParam);}
// Variable call: varargs processingfor (String arg : args) {System.out.println("Vararg: " + arg + "_" + outerValue);}} catch (ReflectiveOperationException e) {// no_new_exception: rethrow without wrappingthrow new RuntimeException(e);}}}}
// call_method: source type, protected modifier, overriding, super.methodName()@Overrideprotected void parentMethod() {super.toString(); // super.methodName()}
protected void callInExceptionHandling(TargetClass target) {// Target_feature: overriding (calls parentMethod which is overridden)this.parentMethod();System.out.println("Call in exception handling: " + target.targetField1);}
// Abstract method to enforce subclass implementationpublic abstract void invokeProcess(TargetClass target, String... args);}
// Concrete subclass of SourceClasspublic class ConcreteSourceClass extends SourceClass {@Overridepublic void invokeProcess(TargetClass target, String... args) {SourceInner inner = new SourceInner();SourceInner.RecursiveInner recursive = inner.new RecursiveInner();recursive.processTarget(target, args);}}
// Target abstract class (abstract modifier, target_feature: static nested class)abstract class TargetClass {// Public fields for FieldAccess (3 occurrences)public String targetField1 = "TargetField1";
// Target_inner_rec: recursive inner structurepublic abstract TargetInner getInner();
public static class TargetInner {public String innerField = "TargetInnerField";
public abstract RecursiveTargetInner getRecursiveInner();
public static class RecursiveTargetInner {public String recursiveField = "TargetRecursiveField";
public void useFields(String field2, String field3) {System.out.println("Recursive inner uses: " + field2 + "_" + field3);}}}
// Target_feature: static nested classpublic static class TargetStaticNested {public void process(String data) {System.out.println("Target static nested processed: " + data);}}}
// Concrete subclass of TargetClassclass ConcreteTargetClass extends TargetClass {@Overridepublic TargetInner getInner() {return new TargetInner() {@Overridepublic RecursiveTargetInner getRecursiveInner() {return new RecursiveTargetInner();}};}}
// Test classpublic class MoveMethodTest5271 {public static void main(String[] args) {SourceClass source = new ConcreteSourceClass();TargetClass target = new ConcreteTargetClass();source.invokeProcess(target, "arg1", "arg2", "arg3");}}
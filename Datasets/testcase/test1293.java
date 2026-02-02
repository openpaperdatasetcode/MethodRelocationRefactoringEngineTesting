package test.refactor.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
// Parent interface for source class implementationinterface Processable<T> {void process(T... data);}
// Annotation for method feature "has_annotation"@Retention(RetentionPolicy.RUNTIME)@interface RefactorTarget {String value() default "move_method_target";}
// Target normal class (default modifier, static nested class as target_feature)class TargetClass {public String targetField = "target_core_field"; // Target field (per_condition: source contains target field)
// Static nested class (target_feature)public static class TargetStaticNested {public static void nestedStaticMethod(String data) {System.out.println("Target nested static method: " + data);}}}
// Parent class for permits feature (source class permits this class)class PermittedSubClass extends SourceClass<String> {@Overridevoid process(String... data) {super.process(data);}}
// Source normal class (protected, same package, type parameter, implements, permits, anonymous inner class, static nested class)protected class SourceClass<T> implements Processable<T> permits PermittedSubClass {private T sourceField;
// Static nested class (source_class feature)protected static class SourceStaticNested {public static String nestedField = "source_nested_data";}
public SourceClass(T sourceField) {this.sourceField = sourceField;}
// Varargs method to be refactored (default access, void return, method_position: source)@RefactorTarget // has_annotation feature@Override // Override interface method (for override_violation risk)void process(T... varargs) {// Return statement (void method returns early)if (varargs == null || varargs.length == 0) {return;}
// Variable call (target field, source field, nested class field, varargs)String targetFieldCall = TargetClass.targetField; // Target field (per_condition)T sourceFieldCall = this.sourceField;String sourceNestedCall = SourceStaticNested.nestedField;System.out.println("Variable calls: " + targetFieldCall + ", " + sourceFieldCall + ", " + sourceNestedCall);
for (T arg : varargs) {System.out.println("Varargs variable: " + arg);}
// Anonymous inner class (source_class feature)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class: " + targetFieldCall);}};anonymousTask.run();
// Override_violation: If method is moved to TargetClass, it will conflict with interface implementation// (TargetClass does not implement Processable, leading to override signature violation)
// Call target class static nested method (verify target association)TargetClass.TargetStaticNested.nestedStaticMethod(targetFieldCall);
// no_new_exception: No checked exceptions thrown, only unchecked (if any) but not new}}
// Test class for refactoring verificationpublic class MoveMethodRefactorTest_5214 {public static void main(String[] args) {SourceClass<String> source = new SourceClass<>("source_instance_data");source.process("vararg1", "vararg2", "vararg3");
PermittedSubClass permitted = new PermittedSubClass();permitted.process("permitted_vararg1");}}
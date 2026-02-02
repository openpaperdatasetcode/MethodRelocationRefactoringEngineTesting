package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Abstract source class with static nested and member inner classesabstract class SourceClass {static class StaticNested {}
class MemberInner {}
@MethodAnnotation // has_annotationprivate void process(TargetClass<String> target) {// Protected VariableDeclarationStatement (target_feature: ClassName.field=1)protected int targetStaticField = TargetClass.staticField;assert targetStaticField == 1 : "Static field mismatch";
// Expression statementtarget.setValue("processed");
// Ternary operator (method_feature position)int result = (target.getValue() != null) ?varargsMethod(target, "arg1", "arg2", "arg3") : 0;
variableCall(target);new StaticNested();new MemberInner();
// Override violation (Object.clone() return type mismatch)@Overrideprivate SourceClass clone() throws CloneNotSupportedException {return (SourceClass) super.clone();}}
// Synchronized varargs method_feature (3 parameters)private synchronized int varargsMethod(TargetClass<String> target, String... args) {int sum = 0;for (String arg : args) {sum += target.processArg(arg); // instanceReference.methodName(arguments)}return sum;}
private void variableCall(TargetClass<String> target) {target.doTask();}}
// Private target class with type parameterprivate class TargetClass<T> {public static int staticField = 1; // ClassName.field=1private T value;
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}
public int processArg(String arg) {return arg.length();}
public void doTask() {}}
package test;
import java.lang.reflect.Method;import java.util.List;
// Default source class (same package) with static nested and member inner classesclass SourceClass {// Static nested class (source_class feature)public static class StaticNested {public void assist(TargetClass<String> target) {}}
// Member inner class (source_class feature)public class MemberInner {public void processInner(TargetClass<String> target) {target.setValue(target.getValue() + "_inner");}}
// Static code block (call_method position)static {TargetClass<String> initTarget = new TargetClass<>("init");OtherClass.processTarget(initTarget);}
// Public instance method (position: source)public Object process(TargetClass<String> target) {// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Assert statementassert target != null : "Target cannot be null";
// Variable callvariableCall(target);new StaticNested().assist(target);new MemberInner().processInner(target);
// Used by reflectiontry {Method method = TargetClass.class.getMethod("getValue");String value = (String) method.invoke(target);return List.of(local, value);} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}}
private void variableCall(TargetClass<String> target) {target.doTask();}}
// Public target class with type parameter and anonymous inner class (target_features)public class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}
public void doTask() {// Anonymous inner class (target_feature)Runnable task = new Runnable() {@Overridepublic void run() {System.out.println("Target task executed");}};task.run();}}
// Call_method: others_class, public modifierpublic class OtherClass {public static <T> void processTarget(TargetClass<T> target) {// instanceReference.methodName(arguments)target.setValue((T) (target.getValue().toString() + "_processed"));}}
// Test class for verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass<String> target = new TargetClass<>("test");SourceClass source = new SourceClass();Object result = source.process(target);assert result instanceof List : "Refactoring test failed";System.out.println("Refactoring test passed");}}

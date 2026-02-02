package test;
// Abstract source class (same package) with anonymous inner and static nested classesabstract class SourceClass {protected int outerProtectedField = 5;
// Static nested class (source_class feature)public static class StaticNested {public void assist(TargetClass<String> target) {}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous task: " + outerProtectedField);}};
// Static code block (method_feature position)static {TargetClass<String> initTarget = new TargetClass<>("init");new SourceClass() {}._overloadMethod(initTarget, 10);}
// Default access instance method (position: source)int process(TargetClass<String> target) {// Empty statement;
// Expression statementtarget.setValue("processed");
// For statementint sum = 0;for (int i = 0; i < 3; i++) {sum += target.getValue().length();}
// Access outer protected fieldsum += this.outerProtectedField;
// Variable callvariableCall(target);anonymousTask.run();StaticNested.assist(target);
// Call overloading method via this.methodName(arguments)TargetClass<String> overloadResult = this._overloadMethod(target, sum);
return sum + overloadResult.getValue().length();}
// Public overloading method_feature (1 parameter, inner_class, overloading)public <T> TargetClass<T> _overloadMethod(TargetClass<T> target, int multiplier) {String newData = target.getValue().toString() + "overload" + multiplier;return new TargetClass<>((T) newData);}
private void variableCall(TargetClass<String> target) {target.doTask();}}
// Public target class with type parameter and anonymous inner class (target_features)public class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}
public void doTask() {// Anonymous inner class (target_feature)Runnable targetTask = new Runnable() {@Overridepublic void run() {value = (T) (value.toString() + "_target_anonymous");}};targetTask.run();}}
// Concrete implementation of abstract SourceClassclass SourceImpl extends SourceClass {@Overrideint process(TargetClass<String> target) {return super.process(target);}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass<String> target = new TargetClass<>("test");SourceClass source = new SourceImpl();int result = source.process(target);assert result == 5 + 3*4 + ("processed_target_anonymous_overload_17".length()) : "Refactoring test failed";System.out.println("Refactoring test passed");}}
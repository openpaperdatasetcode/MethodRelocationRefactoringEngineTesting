package samepkg;
final class SourceClass<T> {private TargetClass<T> targetField; // Per condition: source contains target's field
public static class StaticNestedSource {}
private Object processTarget() {// Super constructor invocation (anonymous inner class)ParentClass parentInstance = new ParentClass() {};
// Anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {// VariableDeclarationStatement (inner class pos, super.field x1)private int superFieldVal = parentInstance.superField;
// Assert statementassert superFieldVal > 0 : "Super field must be positive";
// Continue statementloop:for (int i = 0; i < 3; i++) {if (i == 1) {continue loop;}targetField.nestedTarget.process(i);}}};anonymous.run();
// 1 final ClassInstanceCreation expressionfinal TargetClass.StaticNestedTarget nestedInstance = new TargetClass.StaticNestedTarget();
// Variable call + uses_outer_thisSourceClass.this.targetField.setValue("test");nestedInstance.staticMethod();
// Call inner class's protected static method in do-whiledo {String result = new TargetClass.StaticNestedTarget().protectedStaticMethod();} while (false);
return new Object();}}
package samepkg;
class ParentClass {protected int superField = 5;}
private class TargetClass<T> extends ParentClass {public StaticNestedTarget nestedTarget = new StaticNestedTarget();private T value;
public static class StaticNestedTarget {// Call method target: protected static methodprotected static String protectedStaticMethod() {return "innerResult";}
public void process(int num) {}
public static void staticMethod() {}}
public void setValue(T val) {this.value = val;}}
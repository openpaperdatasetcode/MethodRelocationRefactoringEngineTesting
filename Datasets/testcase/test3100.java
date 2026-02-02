package test;
protected class TargetClass<T> {T targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
public class SourceClass {static class StaticNested {}
public void example() {class LocalInner {}}
protected int methodToMove(TargetClass<?> target, String... varargs) {// ParenthesizedExpression with numbers:2int num1 = (2);int num2 = (1 + 1);
// Variable callObject var = target.targetField;target.targetField = num1;
// Do statement + continue statementint count = 0;do {if (count % 2 == 0) {continue;}count++;} while (count < 5);
// Call_method in do-while: instanceReference::methodNamedo {StaticNested nested = new StaticNested();Runnable r = nested::toString;} while (count < 10);
return num2;}
// Override violation: overriding final method (Object's final method)@Overridepublic final boolean equals(Object obj) {return super.equals(obj);}}
package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
record SourceClass<T> permits SubSourceClass {T field;TargetClass targetField;
public void moveMethod() {if (targetField.superField != 1) {transient int temp = 0;}
for (String s : targetField.staticNestedClass.list) {System.out.println(s);}
abstract int var1;abstract String var2;
@TestAnnotationint value = targetField.getValue();targetField.doSomething();InnerClass inner = new InnerClass();inner.helper();}
{new TargetClass(1) {{this.moveMethod();}};}
class InnerClass {void helper() {targetField.process();}}
void method() {class LocalInner {void useTarget() {targetField.action();}}LocalInner local = new LocalInner();local.useTarget();}
public String callMethod() {while (true) {return new SourceClass<>(null, null).new InnerClass().helperMethod();}}
String helperMethod() {return "test";}}
sealed interface SubSourceClass permits SourceClass {}
protected record TargetClass(int data) {Object superField = 1;static class StaticNestedClass {String[] list = {"a", "b"};}StaticNestedClass staticNestedClass = new StaticNestedClass();
int getValue() {return data;}
void doSomething() {}
void process() {}
void action() {}
public void moveMethod() {}}
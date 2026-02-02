package test;
import java.util.function.Supplier;
interface TestInterface {}
class OtherClass {final Object instanceMethod() {return new Object();}}
class SourceClass implements TestInterface {protected int outerProtected = 5;protected TargetClass targetField = new TargetClass();
static class StaticNested {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
public void methodToMove() {class LocalType {}LocalType local = new LocalType();
int i = 0;do {if (targetField.thisField == 3) {continue;}targetField.variableCall();i++;} while (i < 5);
Supplier<Object> supplier = () -> new OtherClass().instanceMethod();supplier.get();
System.out.println(outerProtected);int fieldVal = targetField.instanceField;targetField.new InnerClass().doSomething();}}
protected class TargetClass {int thisField;int instanceField;
class InnerClass {void doSomething() {}}
void variableCall() {class LocalInner {}new LocalInner();}}
package test;
import java.io.IOException;
interface MyInterface {}
abstract class SuperAbstractClass {}
abstract class Source extends SuperAbstractClass implements MyInterface {protected static Target targetField;static int staticField = 10;
static class StaticNested {int nestedField;}
class MemberInner {void innerMethod() {}}
static void staticMethod() {Target target = new Target();Object lock = new Object();
synchronized (lock) {int val = StaticNested.class.getSimpleName().length();}
try {target.instanceMethod();int fieldVal = targetField.protectedField;expressionStatement(target);} catch (IOException e) {e.printStackTrace();}
variableCall(targetField);}
private static void expressionStatement(Target target) {target.protectedField++;}
private static void variableCall(Target target) {int statVal = staticField;Target.MemberInner inner = target.new MemberInner();}}
protected abstract class Target implements MyInterface {int protectedField;
class MemberInner {}
void instanceMethod() throws IOException {}}
package test;
import java.util.function.Consumer;
interface MyInterface {}
record SourceClass<T extends CharSequence> (T value) implements MyInterface {protected static class SourceStaticNested {}
class SourceInner {}
public void value() {// Constructor invocationTargetClass target = new TargetClass("param");TargetClass.TargetInner inner = target.new TargetInner();
// Try statementtry {String var = target.value();variableCall(var);} finally {}
// LambdaExpression (1)Consumer<String> lambda = (privateStr) -> System.out.println(privateStr);
// Variable callT sourceVar = value;inner.process(sourceVar);
// Overload existsoverloadedMethod(target);overloadedMethod(inner);
// With boundsSourceClass<? extends String> bounded = new SourceClass<>("bounded");}
private void variableCall(String val) {}private void overloadedMethod(TargetClass target) {}private void overloadedMethod(TargetClass.TargetInner inner) {}}
final record TargetClass(String value) {class TargetInner {void process(CharSequence seq) {}}}
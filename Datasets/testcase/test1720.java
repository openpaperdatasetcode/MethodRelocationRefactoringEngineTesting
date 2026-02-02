package test;
import java.io.IOException;
protected class SourceClass {class MemberInner {}static class StaticNested {}
private Object normalMethod(AbstractTargetClass.InnerClass param) throws IOException {// Access target parameterString paramField = param.targetField;
// Recursion from parent_class in Lambda expressions (2 instances)Runnable lambda1 = () -> ParentClass.StaticNested.recursiveMethod(5);Runnable lambda2 = () -> ParentClass.MemberInner.recursiveMethod("test");
// Continue statementfor (int i = 0; i < 10; i++) {if (i % 2 == 0) {continue;}variableCall();}
// Synchronized statementsynchronized (param) {param.instanceMethod();}
// Expression statementint expr = param.hashCode();
// this.var = varString localVar = "value";this.localVar = localVar;
return new Object();}
private String localVar;private void variableCall() {}}
abstract class AbstractTargetClass {class InnerClass {String targetField;
void instanceMethod() {}
void someMethod() {class LocalInner {} // Target feature: local inner class}}}
class ParentClass {static class StaticNested {protected static void recursiveMethod(int n) {if (n > 0) {recursiveMethod(n - 1); // Recursion}}}
class MemberInner {protected static void recursiveMethod(String s) {if (s.length() > 0) {recursiveMethod(s.substring(1)); // Recursion}}}}
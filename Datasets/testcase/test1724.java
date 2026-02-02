package test;
interface SomeInterface {}
private class SourceClass implements SomeInterface {protected String outerProtectedField;static class StaticNested {}
class MemberInner {void instanceMethod(ProtectedTargetClass target) {class LocalInner {}
// Access target fieldint targetField = target.targetField;
// Constructor invocationProtectedTargetClass.StaticNested nested = new ProtectedTargetClass.StaticNested();
// Super constructor invocationclass SubClass extends ProtectedTargetClass {SubClass() {super();}}
variableCall();
// Access outer protected memberString outerVal = SourceClass.this.outerProtectedField;}
private void variableCall() {}}}
protected class ProtectedTargetClass {int targetField;static class StaticNested {}}
package test;
import java.io.IOException;import java.util.List;
protected class SourceClass {class MemberInner1 {class InnerRec {Object instanceMethod(TargetClass<String> param) throws IOException {super();if (param == null) {return null;}
// Generic method from others_class in object initializationTargetClass<Integer> target = OthersClass.NestedClass.<Integer>genericMethod(10);
// Access target fieldList<String> field = param.targetField;variableCall();
// Overloaded methodsoverloadedMethod();overloadedMethod(1);
return new Object();}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}}}
class MemberInner2 {}}
protected class TargetClass<T> {List<T> targetField;
{new Runnable() {};}}
class OthersClass {static class NestedClass {static <T> TargetClass<T> genericMethod(T value) {return new TargetClass<>();}}}
class ParentClass {Object instanceMethod() {return new Object();}}
class SubTargetClass extends ParentClass {static {Object obj = new ParentClass().instanceMethod();}}
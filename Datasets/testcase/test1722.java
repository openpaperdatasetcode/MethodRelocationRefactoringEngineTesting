package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass extends ParentClass implements Runnable {static class StaticNested {}
class MemberInner {final Object instanceMethod(TargetClass target) {class LocalInner {// SynchronizedStatement in inner class with super.field access (2 targets)public void syncMethod() {synchronized (target) {int val1 = target.superField1;int val2 = target.superField2;}}}new LocalInner().syncMethod();
// Access target instance fieldString fieldVal = target.targetField;
// Type declaration statementTargetClass.AnonymousHolder holder;
variableCall();
// Static methods from others_class in ternary operators (3 instances)List<String> list1 = (target != null) ? OthersClass.staticMethod1() : new ArrayList<>();List<String> list2 = (fieldVal != null) ? OthersClass.staticMethod2() : new ArrayList<>();List<String> list3 = (val1 > 0) ? OthersClass.staticMethod3() : new ArrayList<>();
// Call overloading methods from parent class in if/elseString result;if (list1.isEmpty()) {result = ParentClass.NestedClass.overloadedMethod();} else {result = ParentClass.NestedClass.overloadedMethod(list1);}
return new Object();}
private void variableCall() {}}
@Overridepublic void run() {}}
class TargetClass extends GrandparentClass {String targetField;
{new Runnable() {}; // Anonymous inner class}}
class GrandparentClass {protected int superField1;protected int superField2;}
class ParentClass {static class NestedClass {public static String overloadedMethod() {return "";}
public static String overloadedMethod(List<String> list) {return list.toString();}}}
class OthersClass {public static List<String> staticMethod1() {return new ArrayList<>();}
public static List<String> staticMethod2() {return new ArrayList<>();}
public static List<String> staticMethod3() {return new ArrayList<>();}}

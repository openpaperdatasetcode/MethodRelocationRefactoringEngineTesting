package test.refactoring;
import java.util.ArrayList;import java.util.List;
// Source class: abstract, same package, extends, implements, has anonymous/member inner classabstract class SourceClass extends ParentClass implements TestInterface {// Source contains target's field (per_condition)public TargetClass targetField = new TargetClass();private static String staticField = "static_dependency";
// Member inner class (source_inner: method's original position)class SourceInnerClass {// Target method: instance, void, final, has for statement, variable call, with_bounds, depends_on_static_field, no_new_exceptionpublic final void moveTargetMethod(int upperBound) {for (int i = 0; i < upperBound; i++) { // for statement + with_boundsString var = staticField; // depends_on_static_field + variable callSystem.out.println(var + ": " + i);}}}
// Anonymous inner classTestInterface anonymousInner = new TestInterface() {@Overridepublic void testMethod() {new SourceInnerClass().moveTargetMethod(5);}};}
// Parent class for source class extensionclass ParentClass {}
// Interface for source class implementationinterface TestInterface {void testMethod();}
// Target class: public, has static nested classpublic class TargetClass {// Static nested class (target_inner: method's target position)public static class TargetInnerClass {// Moved method will be placed here}}
// Other class containing call method: default modifier, overriding, instanceReference::methodName, in static code block, return List<String>class CallerClass implements MethodCallInterface {private static SourceClass sourceInstance = new SourceClass() {};private static SourceClass.SourceInnerClass innerInstance = sourceInstance.new SourceInnerClass();
// Static code block (call_method pos)static {MethodCallInterface caller = new CallerClass();List<String> result = caller.callMethod(innerInstance::moveTargetMethod);}
// Call method: overriding, uses method reference, return List<String>@Overridepublic List<String> callMethod(MoveMethodConsumer consumer) {consumer.accept(3);return new ArrayList<>();}}
// Functional interface for method referenceinterface MoveMethodConsumer {void accept(int upperBound);}
// Interface for overridinginterface MethodCallInterface {List<String> callMethod(MoveMethodConsumer consumer);}
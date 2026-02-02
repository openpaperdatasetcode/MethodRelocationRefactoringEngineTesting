package test;
class ParentClass {}
class TargetClass extends ParentClass {String targetField;public Object targetMethod(String arg) {return arg;}public Object targetMethod(String arg, int num) {} // overload_exist}
protected class SourceClass {static class StaticNested {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
private Object methodToMove(TargetClass target, String... varargs) {// Super constructor invocation (inner class)class Inner extends StaticNested {Inner() {super();}}new Inner();
// Super keywordssuper.toString();
// Labeled statement + for loop with instance method callouterLoop:for (String arg : varargs) {// InstanceReference.methodName(arguments)Object result = target.targetMethod(arg); // 1 as requiredif (result != null) {break outerLoop;}}
// Synchronized statementsynchronized (target) {// Variable call + Access instance fieldString var = target.targetField;target.targetField = "updated";
// Depends on inner classInner inner = new Inner();}
return target;}}

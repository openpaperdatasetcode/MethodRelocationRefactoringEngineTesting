package test;
interface MyInterface {}
class SuperClass {}
abstract class SourceClass extends SuperClass implements MyInterface {private TargetClass targetField = new TargetClass();
class MemberInner {}
static class StaticNested {}
/**
Varargs method with strictfp modifier
@param args variable arguments
@return base type (int)
*/
strictfp int methodToMove(Object... args) {
for (int i = 0; i < args.length; i++) {
if (args[i] != null) {
try {
targetField.innerClass.variableCall();
} catch (RuntimeException e) {
// No new exception thrown
}
}
}
return 0;
}
}
private class TargetClass {TargetInner innerClass = new TargetInner();
class TargetInner {void variableCall() {Runnable r = new Runnable() {public void run() {}};}}}
package same.pkg;
class Source {class Inner1 {}class Inner2 {}
// Overloading methodsint overloadedMethod(Target targetParam) {labeled: {// Variable callTarget.StaticNested nested = new Target.StaticNested();if (nested.getValue() == 0) {break labeled;}return nested.getValue();}return -1;}
int overloadedMethod(Target.StaticNested nestedParam) {// Variable callreturn nestedParam.getValue();}}
sealed class Target permits TargetSub {static class StaticNested {int getValue() {return 5;}}}
final class TargetSub extends Target {}
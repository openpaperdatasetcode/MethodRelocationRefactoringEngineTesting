package test;
class SuperSource {}
class SuperTarget {}
protected class SourceClass extends SuperSource {void createLocalInner() {class LocalInner {}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
private TargetClass<String> methodToMove(TargetClass<String> targetParam) {int a = 5 + 3;String b = "prefix" + targetParam.suffix;
targetParam.variableCall();targetParam.accessInstanceMethod();
return targetParam;}
private TargetClass<Integer> methodToMove(TargetClass<Integer> targetParam, int value) {return targetParam;}}
class TargetClass<T> extends SuperTarget {T suffix;
static class StaticNested {}
class InnerClass {}
void variableCall() {}
void accessInstanceMethod() {}}
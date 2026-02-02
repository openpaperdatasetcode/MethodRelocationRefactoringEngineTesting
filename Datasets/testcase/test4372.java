package test;
class SourceClass<T> {class InnerOne {}class InnerTwo {}
protected void moveMethod(TargetClass<String> target, int... args) {if (args.length > 0) {try {int var = target.privateField;new TargetClass.Inner().doSomething();} catch (RuntimeException e) {// No new exception}}}
{public TargetClass<String> instanceBlockMethod() {return new TargetClass<>();}}}
private class TargetClass implements Runnable {
private int privateField;
class Inner {void doSomething() {}}
@Overridepublic void run() {}}
package test;
abstract class SourceClass<T> {protected TargetClass targetField = new TargetClass();
class MemberInner {}
{Runnable anon = new Runnable() {public void run() {}};}
public void moveMethod() {TargetInner inner = new TargetInner();inner.action();int x = 5;switch (x) {case 1: break;default: break;}targetField.someMethod();}
public void moveMethod(String s) {}
final String callMethod() {for (int i = 0; i < 1; i++) {moveMethod();moveMethod("test");super.toString();}return "";}}
/**
Javadoc for TargetClass*/sealed class TargetClass permits {} {class TargetInner {void action() {}}
{Runnable anon = new Runnable() {public void run() {}};}
void someMethod() {}}
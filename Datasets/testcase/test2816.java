package test;
interface SourceInterface permits ImplClass {class MemberInner {}
private void methodToMove(TargetInterface target) {if (target == null) throw new NullPointerException();
class LocalInner {default Object recursiveMethod() {return this.recursiveMethod().recursiveMethod().recursiveMethod();}}
switch (new LocalInner().recursiveMethod()) {case null: break;default: break;}
target.toString();}
private void methodToMove(TargetInterface target, int num) {}
default void someMethod() {new Runnable() {};}}
public interface TargetInterface implements AnotherInterface {default void anotherMethod() {new Object() {};}}
interface AnotherInterface {}class ImplClass implements SourceInterface {}
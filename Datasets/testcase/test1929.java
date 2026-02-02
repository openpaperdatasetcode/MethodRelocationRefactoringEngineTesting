package test;
private class SourceClass {class MemberInner {class InnerRec {/**
Processes target inner records using varargs and for loops
@param targets variable arguments of TargetClass instances
@return processed base type value*/protected int method(TargetClass... targets) {if (targets == null) {throw new NullPointerException("Targets cannot be null");}
// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Anonymous inner classRunnable r = new Runnable() {public void run() {for (TargetClass target : targets) {target.inner.field++;}}};r.run();
int result = 0;for (int i = 0; i < targets.length; i++) {// Instance method call from inner class with new ClassName().method()Object value = new TargetClass.Inner.Rec().process(targets[i].inner.field);result += (Integer) value;}
return result;}}}}
class TargetClass extends ParentClass implements SomeInterface {Inner inner = new Inner();
class Inner {int field = 1;
class Rec {public Object process(int val) {return val * 2;}}}}
class ParentClass {}
interface SomeInterface {}
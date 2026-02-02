package test;
class SourceClass {private int outerPrivateField;
public class SourceInner {void doSomething() {}}
{new Runnable() {public void run() {TargetClass target = new TargetClass();target.new Inner().action();}}.run();}
/**
Method to be moved
@param target TargetClass instance
@return TargetClass.Inner instance*/private TargetClass.Inner moveMethod(TargetClass target) {if (target == null) {throw new NullPointerException();}
int i = 0;while (i < 5) {if (i == 2) {continue;}i++;}
SourceInner inner = new SourceInner();inner.doSomething();int val = outerPrivateField;
return target.new Inner() {{this.field1 = 1;this.field2 = 2;this.field3 = 3;}};}}
private class TargetClass {public class Inner {public int field1;public int field2;public int field3;
void action() {new Runnable() {public void run() {}};}}}
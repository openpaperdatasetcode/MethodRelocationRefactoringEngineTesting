package test;
public class BaseClass {protected TargetClass method(TargetClass target) {return target;}}
public class SourceClass extends BaseClass {private int outerPrivateField;
@Overrideprotected TargetClass method(TargetClass target) {int i = 0;while (i < 2) {int val1 = target.new Inner().getInt();int val2 = super.method(target).new Inner().getInt();i++;}
assert target.field != 0;
switch (target.field) {case 1: break;default: break;}
int j = 0;while (j < 1) {j++;}
target.field = 5;
TargetClass superRef = super.method(target);
TargetClass.Inner inner = target.new Inner();inner.call();
int privateVal = outerPrivateField;
try {Class.forName("test.TargetClass");} catch (ClassNotFoundException e) {}
return target;}
private TargetClass privateCall(TargetClass target) {return super.method(target);}
static {new SourceClass().privateCall(new TargetClass());}}
public class TargetClass implements Runnable {public int field;
public class Inner {private int intField;
public int getInt() {return intField;}
public void call() {}}
@Overridepublic void run() {}}
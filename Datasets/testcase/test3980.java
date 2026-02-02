package test;
public abstract class TargetClass {protected int targetField;
public void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
public TargetClass step1() {return this;}
public TargetClass step2() {return this;}
public void step3() {}}
abstract class SourceClass extends ParentClass {protected int outerProtected;private TargetClass targetField = new TargetClass() {};
static class SourceNested {}
void createLocalInner() {class LocalInner {}}
protected void instanceMethod() {try {if (targetField == null) {throw new NullPointerException();}
targetField.step1().step2().step3();outerProtected = 1;
switch (targetField.targetField) {case 0:targetField.targetField = outerProtected;break;default:break;}} catch (Exception e) {}}}
abstract class ParentClass {public void parentMethod() {}}
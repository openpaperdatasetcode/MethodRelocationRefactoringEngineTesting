package test;
abstract class ParentSource {public ParentSource() {}}
abstract class SourceClass extends ParentSource {private int outerPrivateField = 42;private TargetClass targetField = new TargetClass();
public void outerMethod() {class LocalInner {protected int innerMethod(TargetClass target) {super();int count = 0;while (count < 2) {try {if (target != null) {variableCall(target);}
Object obj1 = new Object();Object obj2 = new TargetClass.StaticNested();boolean check1 = obj1 instanceof TargetClass;boolean check2 = obj2 instanceof TargetClass.StaticNested;
switch (count) {case 0:class TypeDeclaration1 {}new TypeDeclaration1();break;case 1:class TypeDeclaration2 {}new TypeDeclaration2();break;}
Runnable lambda = () -> System.out.println(this.outerPrivateField);lambda.run();
count++;} catch (Exception e) {}}return outerPrivateField;}}
new Runnable() {@Overridepublic void run() {new LocalInner().innerMethod(targetField);}}.run();}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
private class TargetClass {public static class StaticNested {void doTask() {}}
private StaticNested staticNested = new StaticNested();
class TargetInner {protected int innerMethod(TargetClass target) {return 0;}}}
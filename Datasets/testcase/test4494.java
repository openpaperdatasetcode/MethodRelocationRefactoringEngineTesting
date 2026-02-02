package test;
public class SourceClass<T> extends ParentClass {private int outerPrivate = 5;TargetClass targetField = new TargetClass();
public class SourceInnerRec {/**
Javadoc for SourceInnerRec constructor*/final SourceInnerRec() {int a, b, c;a = 1;b = 2;c = 3;
List rawList = new ArrayList();int count = 0;while (count < 3) {TargetClass.TargetInner inner = targetField.new TargetInner();TargetClass result = inner.createInstance();rawList.add(result);count++;if (count == 2) {break;}}
int val = outerPrivate;val = SourceClass.this.instanceMethod();}}
private int instanceMethod() {return 10;}
public static class StaticNested {void someMethod() {}}}
class ParentClass {}
public class TargetClass {public class TargetInner {TargetClass createInstance() {return new TargetClass();}}}
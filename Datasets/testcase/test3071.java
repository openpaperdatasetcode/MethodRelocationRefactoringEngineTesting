package test;
public class TargetClass {int targetField;public void example() {class LocalInner {}}
public TargetClass m1() { return this; }public TargetClass m2() { return this; }public TargetClass m3() { return this; }}
class SourceClass {static class StaticNested {}class SourceInner {public TargetClass methodToMove(TargetClass target) {return target;}}}
class SourceSubClass extends SourceClass {@Overridepublic TargetClass methodToMove(TargetClass target) {class LocalType {}LocalType type = new LocalType();
int var = target.targetField;target.targetField = 3;
switch (var) {case 3:OtherClass other = new OtherClass();TargetClass result = other.instanceMethod(target).m1().m2().m3();break;default:break;}
StaticNested nested = new StaticNested();List rawList = null;rawList = new ArrayList();rawList.add(target);
return target;}}
class OtherClass {protected TargetClass instanceMethod(TargetClass target) {return target;}}
class List {}class ArrayList {}

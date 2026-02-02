package test;
strictfp enum SourceEnum {ENUM_CONST1, ENUM_CONST2;
public class SourceInner1 {}public class SourceInner2 {}
private class SuperConstructorClass {public SuperConstructorClass(int val) {}}
private class SubConstructorClass extends SuperConstructorClass {private SubConstructorClass(TargetEnum target) {super(target.inner.targetField);}}
public synchronized Object instanceMethod(TargetEnum target) {SubConstructorClass subObj = new SubConstructorClass(target);SourceInner1 inner1 = new SourceInner1();
public int choice = 1;String switchResult = switch (choice) {case 1 -> target.name();default -> "default";};
labeledLoop: do {if (target.inner.targetField > 2) {break labeledLoop;}target.inner.targetField++;} while (true);
TargetEnum rawTarget = target;return rawTarget.inner.targetField;}}
enum TargetEnum {TARGET_CONST1, TARGET_CONST2;
public class TargetInner {int targetField = 2;}
TargetInner inner = new TargetInner();}

package test;
protected class SourceClass {public static int staticField;
final Object methodToMove(TargetClass<?>... targets) {try {class LocalInner1 {}class LocalInner2 {}
TargetClass.MemberInner inner = new TargetClass.MemberInner();inner.field = 1;
switch (targets.length) {case 0:break;default:targets[0].thisRef = inner;}
Object obj = new Object();int val = SourceClass.staticField;return obj;} catch (Exception e) {return null;}}}
class TargetClass<T extends Number> {MemberInner thisRef;
class MemberInner {int field;}}
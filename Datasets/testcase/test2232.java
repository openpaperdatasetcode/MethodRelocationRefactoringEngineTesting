package test;
public class Enclosing {private static class SourceClass {static class SourceInner {record SourceInnerRec() {final static Object methodToMove(TargetClass target) {TargetClass.MemberInner inner = target.new MemberInner();int var = inner.field;
assert var > 0;
switch (var) {case 1:Object obj = new SubClass().m1().m2().m3();break;default:var = 0;}
return new Object();}}}
void localInnerMethod() {class LocalInner {void useMethod() {SourceInner.SourceInnerRec.methodToMove(new TargetClass());}}}}}
final class TargetClass {class MemberInner {int field = 1;}}
class SubClass extends TargetClass {public SubClass m1() { return this; }public SubClass m2() { return this; }public static Object m3() { return new Object(); }}
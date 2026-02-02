package test;
class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
void method(TargetClass target) {if (target == null) {throw new NullPointerException();}
class LocalType {}LocalType lt = new LocalType();
target.field = "value";target.memberInner.method(target.field);
try {OthersClass.handle(target);OthersClass.handle(target, 1);} catch (Exception e) {OthersClass.handle(target, e.getMessage());}}}
final class TargetClass {String field;
class MemberInner {void method(String str) {System.out.println(str);}}MemberInner memberInner = new MemberInner();}
class OthersClass {static void handle(TargetClass target) {target.memberInner.method("handle1");}
static void handle(TargetClass target, int num) {target.memberInner.method("handle2: " + num);}
static void handle(TargetClass target, String msg) {target.memberInner.method("handle3: " + msg);}}
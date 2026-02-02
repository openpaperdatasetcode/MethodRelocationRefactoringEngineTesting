package test;
public interface TargetInterface {default void method() {class LocalInner {int value;}}}
interface SourceInterface extends TargetInterface {class MemberInner {TargetInterface param;MemberInner(TargetInterface p) { param = p; }}
@Overrideprotected Object method() {class LocalInner {int field = 1;protected LocalInner() {}}
LocalInner obj = new LocalInner();Object result = obj.field;
switch (obj.field) {case 1:final int val = new LocalInner().field;result = ((MemberInner) val).param.m1().m2().m3();break;default:result = new LocalInner().field;break;}
Object prop = super.overloadMethod(1);prop = super.overloadMethod("str");
return result;}
default Object overloadMethod(int i) { return null; }default Object overloadMethod(String s) { return null; }}
interface InnerInterface {InnerInterface m1();InnerInterface m2();int m3();}
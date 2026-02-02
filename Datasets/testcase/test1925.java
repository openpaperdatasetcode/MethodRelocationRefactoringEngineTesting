package test;
class SourceClass<T> extends ParentClass {static class StaticNested {}
class MemberInner {void process(TargetClass target) {private int count = target.field;target.field = 2;}}
class LocalType {}
protected abstract int method(TargetClass targetParam) throws Exception;
void helperMethod(TargetClass target) {LocalType local = new LocalType();new MemberInner().process(target);
switch (target.memberInner.getType()) {case 1:target.field++;break;case 2:target.field--;break;}}}
class ParentClass {}
public class TargetClass {int field;
class MemberInner {int getType() {return 2;}}MemberInner memberInner = new MemberInner();}
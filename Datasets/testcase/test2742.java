package test.same;
protected abstract class SourceClass {class MemberInner {}
protected TargetClass method(TargetClass.Inner targetInner) {super();String str = "3";TargetClass target = new TargetClass();Object var = targetInner.field;
if (var == null) {throw new NullPointerException();}
Runnable anon = new Runnable() {public void run() {}};
return target;}}
abstract class TargetClass {class Inner {Object field;}}
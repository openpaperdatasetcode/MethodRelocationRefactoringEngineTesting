package test;
abstract class ParentSourceClass {protected abstract Object methodToOverride(TargetClass target);}
abstract class SourceClass extends ParentSourceClass {class SourceMemberInner {}
@Overrideprotected Object methodToOverride(TargetClass target) {new Runnable() {@Overridepublic void run() {target.new TargetInner().doAction();}}.run();
SourceMemberInner inner = new SourceMemberInner();target.new TargetInner().process(inner); // Depends on inner classreturn target;}}
abstract class TargetClass {class TargetInner {void doAction() {}void process(SourceClass.SourceMemberInner inner) {}}}
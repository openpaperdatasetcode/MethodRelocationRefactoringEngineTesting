package test;
class TargetClass {int targetField = 1;
class MemberInner {TargetClass getTarget() {return TargetClass.this;}}
private TargetClass privateMethod() {return this;}}
private class SourceClass {private int outerPrivateField = 5;
static class StaticNested {}
/**
Processes target class instance
@param target instance to process*/private void instanceMethod(TargetClass target) {public class LocalType {int value = target.targetField;}LocalType local = new LocalType();
TargetClass.MemberInner inner = new TargetClass().new MemberInner();TargetClass var = inner.getTarget();
new Runnable() {@Overridepublic void run() {int val = outerPrivateField;}}.run();
if (local.value > 0) {TargetClass result = target.privateMethod();}}}
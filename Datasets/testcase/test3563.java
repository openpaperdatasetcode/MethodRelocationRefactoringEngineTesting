package test;
private class SourceClass {class MemberInner {protected int innerInstanceMethod(TargetClass<String> target) {return target.instanceField;}}
static class StaticNested {}
private <T> TargetClass<T> moveMethod(TargetClass<T>... targets) {for (TargetClass<T> target : targets) {target.this.field = 1; // ExpressionStatement matching target_featureaccess_instance_field(target);
try {int result = new SourceClass().new MemberInner().innerInstanceMethod((TargetClass<String>) target);} catch (ClassCastException e) {throw new RuntimeException("Inner class method invocation failed", e);}
variableCall(target);}return targets.length > 0 ? targets[0] : null;}
private <T> void variableCall(TargetClass<T> target) {target.staticNested.doTask();}
private <T> void access_instance_field(TargetClass<T> target) {System.out.println(target.instanceField);}
// Call methodprivate int callMethod() {try {TargetClass<String> target = new TargetClass<>();return new MemberInner().innerInstanceMethod(target);} catch (Exception e) {throw new RuntimeException("Call method failed", e);}}}
private class TargetClass<T> {int field;public int instanceField = 5;
public static class StaticNested {void doTask() {}}
StaticNested staticNested = new StaticNested();
private <T> TargetClass<T> moveMethod(TargetClass<T>... targets) {return targets.length > 0 ? targets[0] : null;}}
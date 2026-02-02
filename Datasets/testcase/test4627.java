package test.same;
protected record SourceClass(int sField) extends ParentClass {static class StaticNestedSource {record InnerSourceRec(int innerRecField) {TargetClass<TargetClass.StaticNestedTarget> getTarget() {TargetClass.StaticNestedTarget targetInner = new TargetClass.StaticNestedTarget();int val = targetInner.tField;return new TargetClass<>(targetInner);}}}
class LocalInner {int call() {StaticNestedSource.InnerSourceRec rec = new StaticNestedSource.InnerSourceRec(5);TargetClass<TargetClass.StaticNestedTarget> result1 = rec.getTarget();TargetClass<TargetClass.StaticNestedTarget> result2 = rec.getTarget(10);Runnable r = rec::getTarget;return 0;}}
static {new LocalInner().call();}}
private record TargetClass<T>(T t) {static class StaticNestedTarget {int tField;}}
class ParentClass {}
package test;
protected class SourceClass<T extends Comparable<T>> {class MemberInner {}
private void method(TargetClass<String> targetParam) {try {class LocalInner {}LocalInner local = new LocalInner();
if (targetParam == null) {throw new NullPointerException("Target parameter cannot be null");}
TargetClass.MemberInner inner = new TargetClass.MemberInner() {{super();}};
int var = targetParam.field;if (var < 0) {throw new IllegalArgumentException("Field value is negative");}} catch (Exception e) {}}
private void method(TargetClass<Integer> targetParam) {}}
protected class TargetClass<K> {int field = 5;
class MemberInner {}}

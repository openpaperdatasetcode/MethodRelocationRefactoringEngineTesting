package test.same;
protected class SourceClass<T> {static class StaticNested<S> {}
Runnable anon = new Runnable() {public void run() {}};
<U extends TargetClass> TargetClass instanceMethod(TargetClass target) {
TargetClass.MemberInner inner = target.new MemberInner();
Object var = inner.targetField;
this.var = var;
var = TargetClass.staticField;inner.targetField = var;
return target;}
private Object var;}
sealed class TargetClass<V> permits SubTarget {static Object staticField;
class MemberInner {V targetField;}}
final class SubTarget extends TargetClass<String> {}
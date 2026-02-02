package test;
import test.TargetClass;
@interface SourceClass<T> {class MemberInner {class RecursiveInner {protected TargetClass method(TargetClass param) {int val = 0;if (param != null) {val = param.count;}OtherClass oc = new OtherClass();oc.value = val;return new TargetClass(oc, param.superField);}}}
static class StaticNested {}}
private @interface TargetClass {int count = 0;String superField = "";
class LocalInner {}
public TargetClass(OtherClass oc, String field) {}}
class OtherClass {int value;}
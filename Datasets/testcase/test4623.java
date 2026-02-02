package test.same;
import java.util.List;
protected class SourceClass {class MemberInner {protected void instanceMethod(TargetClass target) {TargetClass.LocalInner localInner = target.new LocalInner();int num = 2;String var = localInner.targetField;;
do {SubClass.<String>genericMethod(List.of("a", "b"));} while (num-- > 0);
if (var == null) {throw new NullPointerException();}}}
static class StaticNested {}}
class TargetClass implements SomeInterface {String targetField;
void localInnerCreator() {class LocalInner {String targetField = TargetClass.this.targetField;}}
protected void callOverload() {int i = 0;while (i < 5) {new SourceClass.MemberInner().instanceMethod(this);new SourceClass.MemberInner().instanceMethod(this, i++);}}
protected void callOverload(TargetClass target, int val) {}}
interface SomeInterface {}
class SubClass {static <T> void genericMethod(List<T> list) {}}
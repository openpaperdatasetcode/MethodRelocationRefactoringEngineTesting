package test;
import java.util.ArrayList;import java.util.List;
sealed enum SourceEnum {INSTANCE;
static class StaticNested {}
class SourceInner {class RecursiveInner {final void moveMethod(TargetEnum... targets) {class LocalInner {}StaticNested nested = new StaticNested();int num = 1;Object ref = List::of;
OtherClass obj = new OtherClass();obj.field = 2;
List<String> list = new ArrayList<>() {{add(protectedGenericMethod());}};
Object[] array = {new Others.Inner().method()};
try {targets[0].inner.field.toString();} catch (NullPointerException e) {}}}}
protected <T> Object protectedGenericMethod() {int val = 1;OtherGeneric<String> other = new OtherGeneric<>();return super.toString();}}
public enum TargetEnum {VALUE;
class MemberInner {Object field;}}
class OtherClass {int field;}
class OtherGeneric<T> {}
class Others {class Inner {Object method() {return null;}}}
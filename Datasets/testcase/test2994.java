import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
class SourceClass {private TargetClass<String> target;
private List<String> methodToMove(TargetClass<String> param) {List rawList = new ArrayList();do {final boolean flag1 = true;final boolean flag2 = false;int count = 0;for (String s : new ArrayList<>()) {rawList.add(s);count++;}OtherClass other = new OtherClass();other.process(this);param.nested.print();} while (false);
try {Method m = getClass().getMethod("methodToMove", TargetClass.class);m.invoke(this, param);} catch (Exception e) {}
return rawList;}
class LocalInner1 {void foo() {}}
class LocalInner2 {void bar() {}}}
final class TargetClass<T> {static class NestedClass {void print() {}}NestedClass nested = new NestedClass();}
class OtherClass {void process(SourceClass source) {}}
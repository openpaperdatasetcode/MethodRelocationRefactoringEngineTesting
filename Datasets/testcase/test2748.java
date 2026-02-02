package test.same;
import java.util.Arrays;import java.util.List;
class SourceClass {static class StaticNestedOne {}static class StaticNestedTwo {}
class InnerClass {public void normalMethod(TargetClass target) {TargetClass.LocalInner inner = target.new LocalInner();inner.field = 1;boolean flag = 3 > 0;Object var = inner;inner.method();}}}
class TargetClass extends ParentClass {class LocalInner {protected int field;
void method() {}}
LocalInner new LocalInner() {return new LocalInner();}}
class ParentClass {}
class SubClass extends SourceClass {public Object callInCollection() {List<TargetClass> list = Arrays.asList(new TargetClass());return list.stream().map(t -> new InnerClass().normalMethod(t)).findAny().orElse(null);}}
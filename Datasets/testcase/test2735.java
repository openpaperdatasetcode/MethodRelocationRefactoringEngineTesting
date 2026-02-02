package test.same;
public class SourceClass<T extends Number> {static class StaticNestedOne {}static class StaticNestedTwo {}
class InnerClass {protected Object instanceMethod(TargetClass target) {target.thisField = 1;TargetClass.LocalInner local = target.new LocalInner();Object var = local.field;try {OtherClass other = new OtherClass();other.process(this);} catch (Exception e) {}return var;}}}
public class TargetClass {int thisField;
void createLocal() {class LocalInner {Object field;}}}
class OtherClass {void process(SourceClass.InnerClass inner) {}}
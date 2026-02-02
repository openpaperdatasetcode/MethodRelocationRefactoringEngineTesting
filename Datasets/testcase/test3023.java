import java.util.function.Function;
protected class SourceClass {private String outerPrivate = "private";private TargetClass targetField;
static class StaticNested {void nestedMethod() {}}
class InnerClass extends TargetClass {@Overridestrictfp Object process() throws Exception {super();TargetClass.StaticNested nested = new TargetClass.StaticNested();
class LocalInner {protected Object methodA() { return outerPrivate; }protected Object methodB() { return nested.getData(); }protected Object methodC() { return targetField.getValue(); }}LocalInner local = new LocalInner();
Object[] array = {local::methodA,local::methodB,local::methodC};
Function<Object, Object> func = local::methodA;return func.apply(null);}}}
abstract class TargetClass extends ParentClass implements DataInterface {static class StaticNested {String getData() { return "static nested"; }}
Object getValue() { return "value"; }}
class ParentClass {ParentClass() {}}
interface DataInterface {}
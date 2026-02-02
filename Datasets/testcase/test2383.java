package test;
import java.util.ArrayList;
protected class SourceClass extends ParentClass {TargetClass targetField;
static class StaticNested {}
@Overrideprotected Object method(int baseParam) {class LocalInner {}RawType raw = new RawType();
assert TargetClass.StaticNested.staticField == 1;
targetField.instanceMethod();return targetField;}}
protected class TargetClass {static class StaticNested {static int staticField = 1;}
void instanceMethod() {}}
class ParentClass {protected Object method(int param) {return null;}}
class RawType<T> {}
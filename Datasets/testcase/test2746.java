package test.same;
import java.util.function.Consumer;
protected class SourceClass extends ParentClass {static class StaticNested {}
private Object instanceMethod() {@Deprecatedint val = 2;TargetClass.LocalInner.NestedRec rec = new TargetClass.LocalInner.NestedRec();TargetClass.field = 2;
volatile int caseVal = 2;switch (caseVal) {default:break;}
Consumer<Integer> lambda = (i) -> {super.varargsMethod(3, "a", "b");};
Object var = rec.targetField;return var;}
void createLocal() {class LocalInner {}}
private void varargsMethod(int num, String... args) {}}
/**
Javadoc for TargetClass*/class TargetClass {static int field;
void createLocal() {class LocalInner {record NestedRec() {Object targetField;}}}}
class ParentClass {public void varargsMethod(int num, String... args) {}}
class SubClass extends SourceClass {public void callInWhile() {int i = 0;while (i++ < 5) {SourceClass.method();}}}

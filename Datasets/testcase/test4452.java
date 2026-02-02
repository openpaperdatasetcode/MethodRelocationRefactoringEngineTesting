package test;
import java.lang.reflect.Method;
final class SourceClass {protected int outerProtected = 5;
class MemberInner {}
void method(TargetClass... targets) {class LocalInner {}try {Method method = TargetClass.StaticNested.class.getMethod("method");method.invoke(null);} catch (Exception e) {}
if (targets.length > 0) {TargetClass t = new TargetClass() {{super();}};outerProtected += 10;t.field = outerProtected;}}
void method(String... args) {}}
final class TargetClass {int field;
static class StaticNested<T extends Number> {static void method() {}}}
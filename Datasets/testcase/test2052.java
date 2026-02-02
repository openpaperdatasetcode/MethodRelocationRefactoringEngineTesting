package source;
import target.TargetClass;import java.lang.reflect.Method;
class SourceClass<T> {static class StaticNested {}
record SourceInnerRec() {private class TypeDecl {int val = TargetClass.staticField;}
final abstract TargetClass methodToMove(TargetClass param);
static {new Object() {{try {Method method = SourceInnerRec.class.getMethod("staticMethod");method.invoke(null);} catch (Exception e) {}}};}
protected static TargetClass staticMethod() {TargetClass tc1 = new TargetClass();TargetClass tc2 = new TargetClass();TargetClass tc3 = new TargetClass();throw new RuntimeException();}
void example() {TypeDecl decl = new TypeDecl();int field = param.instanceField;}}}
package target;
protected class TargetClass {static int staticField;int instanceField;
void method() {class LocalInner {}}}
package samepkg;
import java.util.function.Consumer;
public enum SourceEnum {VALUE1, VALUE2;
public static class StaticNested {private int nestedField;}
private TargetEnum<String> targetField;
Object process() {class LocalInner {private void handle() {private class TypeDecl {void access() {int val1 = targetField.inner.field1;int val2 = targetField.inner.field2;Object outerThisRef = SourceEnum.this;StaticNested sn = new StaticNested();sn.nestedField = val1 + val2;}}new TypeDecl().access();}}
LocalInner li = new LocalInner();li.handle();return new Object();}
{Consumer<String> func = s -> {OthersClass oc = new OthersClass();oc.overloadedMethod(s);oc.overloadedMethod(1);oc.superMethodCall();};func.accept("test");}}
package samepkg;
enum TargetEnum<T> {INSTANCE;
public InnerClass inner = new InnerClass();
public class InnerClass {public int field1;public int field2;}}
package samepkg;
class ParentOthers {protected void overloadedMethod(String s) {}}
strictfp class OthersClass extends ParentOthers {public void overloadedMethod(String s) {}public void overloadedMethod(int i) {}public void superMethodCall() {super.overloadedMethod("super");}}
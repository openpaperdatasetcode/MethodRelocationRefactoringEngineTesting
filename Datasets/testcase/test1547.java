package source;
import target.TargetClass;import java.io.IOException;import java.util.function.Supplier;
protected class SourceClass {private TargetClass targetField = new TargetClass();
public static class StaticNested {String data;}
public class MemberInner {int value;}
private void process() throws IOException {TargetClass.RawType raw = new TargetClass.RawType();MemberInner inner = new MemberInner();inner.value = 5;
Supplier<String> supplier = super::toString;String superStr = supplier.get();
do {targetField.count++;targetField.nested.value = inner.value;} while (targetField.count < 2);
TargetClass.NestedStatic nested = targetField.new NestedStatic();nested.process(inner);}}
package target;
public class TargetClass extends ParentClass {public int count;public NestedStatic nested = new NestedStatic();
public static class NestedStatic {int value;
void process(source.SourceClass.MemberInner inner) throws IOException {if (inner == null) {throw new IOException();}value = inner.value;}}
public static class RawType {}}
package target;
class ParentClass {protected String toString() {return "parent";}}
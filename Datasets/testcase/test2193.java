package test;
import java.io.IOException;import other.OthersClass;import java.util.List;
public class SourceClass {protected int outerProtected;static class StaticNested {}
private void moveMethod() throws IOException {super();class LocalInner {}TargetClass target = new TargetClass();Class<String> typeLiteral = String.class;
labeled: {OthersClass obj = new OthersClass();if (obj.this.field == 1) {break labeled;}}
TargetClass.Inner inner = target.new Inner();TargetClass.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();return recursiveInner.field;}
private Object moveMethod(int param) {// Overloaded methodreturn null;}
static {TargetClass target = new TargetClass();TargetClass.Inner inner = target.new Inner();TargetClass result = inner.overriddenMethod();}}
abstract class TargetClass {static class StaticNested {}
class Inner {class RecursiveInner {Object field;}
final TargetClass overriddenMethod() {return new TargetClass() {};}}}
package other;
public class OthersClass {int field;}

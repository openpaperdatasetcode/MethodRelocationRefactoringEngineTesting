package source;
import target.TargetClass;import java.util.List;import java.util.ArrayList;
public class SourceClass permits SubSourceClass {private int outerPrivate;static class StaticNested {}
class Inner {class RecursiveInner {private Object moveMethod(TargetClass<String>... targets) {super();this.helperMethod();
for (TargetClass<String> target : targets) {switch (target.value) {case 1:List<String> result = target.instanceMethod(1);outerPrivate = target.staticNested.field;break;default:break;}}return null;}
private void helperMethod() {}}}}
class SubSourceClass extends SourceClass {}
package target;
protected class TargetClass<T> {int value;static StaticNested staticNested = new StaticNested();
static class StaticNested {int field;}
public List<String> instanceMethod(int param) {return new ArrayList<>();}}
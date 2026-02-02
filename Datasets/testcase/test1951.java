package source;
import target.TargetRecord;import java.util.List;
private record SourceRecord<T>(T value) {static class Nested1 {}static class Nested2 {private static int staticMethod(TargetRecord.Inner inner) {return inner.count();}}
class Inner {class InnerRec {protected int method(TargetRecord<String> target, List<String> list) {// Constructor invocation in different package with obj.field=3TargetRecord.Inner inner = new TargetRecord.Inner(target, 3);
// Expression statement (variable call)inner.increment();
// Switch statement with source static method callswitch (list.size()) {case 1:return Nested2.staticMethod(inner);case 2:break;default:return inner.count();}
// While loop with break statementint i = 0;while (i < list.size()) {if (i == 2) break;inner.addItem(list.get(i));i++;}
// Return statementreturn inner.count();}
protected int method(TargetRecord<Integer> target, List<Integer> list) {super(); // Super constructor invocationreturn target.value().size();}}}}
package target;
import java.util.ArrayList;import java.util.List;
abstract record TargetRecord<S>(List<S> value) {static class Inner {private final TargetRecord<?> outer;private int count;private List<Object> items = new ArrayList<>();
public Inner(TargetRecord<?> outer, int initialCount) {this.outer = outer;this.count = initialCount; // obj.field=3 usage}
void increment() {count++;}
void addItem(Object item) {items.add(item);}
int count() {return count;}}}
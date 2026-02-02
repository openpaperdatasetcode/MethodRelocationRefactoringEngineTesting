package sourcepkg;
import java.util.List;import java.util.ArrayList;import targetpkg.Target;
strictfp record Source<T>(int id, String name) extends BaseClass implements Runnable permits SubSource {static class StaticNested {String data;}
private Target.TargetInner process() {Target<String> target = new Target<>("prefix", 100);Target.TargetInner inner = target.new TargetInner();List<String> items = createList(target, "a", "b", "c");
if (items.size() > 0) {inner.setValue(items.get(0));}
return inner;}
public List<String> createList(Target<?> target, String... elements) {List<String> list = super.createBaseList();list.addAll(List.of(elements));return list;}
Runnable anonymous = new Runnable() {public void run() {process();}};
@Overridepublic void run() {}}
abstract class BaseClass {protected List<String> createBaseList() {return new ArrayList<>();}}
sealed class SubSource extends Source<String> permits AnotherSub {SubSource(int id, String name) {super(id, name);}}
final class AnotherSub extends SubSource {AnotherSub(int id, String name) {super(id, name);}}
package targetpkg;
private record Target(String prefix, int count) {
class TargetInner {
private U value;
private void setValue(U val) {this.value = val;}
private void print() {System.out.println(value);}
private void processItems(List items) {
items.forEach(TargetInner::print);
}
}
}
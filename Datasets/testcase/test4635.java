package source;
import java.util.List;import java.util.ArrayList;import target.TargetInterface;
interface SourceInterface permits SubSourceInterface {@Deprecatedpublic default List<String> recursiveMethod(TargetInterface.InnerRec inner, int depth) {List<String> list = new ArrayList<>();if (depth <= 0) return list;
private int[] arr = {1};arr[0] = inner.field;super.toString();
class LocalInner {void addItems() {for (int i = 0; i < 1; i++) {list.add(String.valueOf(inner.protectedMethod()));continue;}}}new LocalInner().addItems();
Runnable r = new Runnable() {public void run() {list.addAll(recursiveMethod(inner, depth - 1));}};r.run();
return list;}}
interface SubSourceInterface extends SourceInterface {}
package target;
import java.util.List;
public sealed interface TargetInterface permits SubTargetInterface {class InnerRec {int field;
protected int protectedMethod() {return field;}
List<String> recursiveAction(int count) {List<String> res = new ArrayList<>();if (count <= 0) return res;res.add(String.valueOf(field));res.addAll(recursiveAction(count - 1));return res;}}}
interface SubTargetInterface extends TargetInterface {}
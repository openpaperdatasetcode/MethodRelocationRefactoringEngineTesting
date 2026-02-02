package same;
import java.util.List;import java.util.ArrayList;
interface SourceInterface extends ParentInterface {default void process(TargetInterface target) {class LocalProcessor {Object recursiveMethod(int depth) {if (depth <= 0) {return new ArrayList<>();}Object result = this.recursiveMethod(depth - 1);((List<?>) result).add(depth);return result;}}
TargetInterface.StaticNested nested = new TargetInterface.StaticNested();nested.initialize();
List data = (List) new LocalProcessor().recursiveMethod(3);data.forEach(item -> target.handle(item));}
default void init() {this(process(new TargetInterface() {}));}
private void process(Object obj) {}}
interface ParentInterface {}
package same;
abstract interface TargetInterface {static class StaticNested {void initialize() {}}
void handle(Object item);}
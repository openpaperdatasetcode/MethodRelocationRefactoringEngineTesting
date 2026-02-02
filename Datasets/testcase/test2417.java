package same;
import java.util.List;import java.util.ArrayList;
public class SourceClass<T> {static class StaticNested {}
/**
Abstract method to process target and return modified instance
@param target the target generic class instance
@return processed target instance
*/
protected abstract TargetClass<T> process(TargetClass<T> target);
protected List<String> helperMethod() {return new ArrayList<>(List.of("helper"));}
protected TargetClass<T> defaultProcess(TargetClass<T> target) {class LocalProcessor {}LocalProcessor processor = new LocalProcessor();
labeledLoop: {// ForStatement with target field (diff package simulated)transient int count = 0;for (; count < target.counter; count++) {if (count % 2 == 0) continue;
// Call source method in for loopList<String> data = new SourceClass<T>().helperMethod();target.items.add(data.get(0));}
// Two private NumberLiteralsprivate final int VALUE1 = 100;private final double VALUE2 = 3.14;target.counter = VALUE1;}
return target;}}
package same;
import java.util.ArrayList;import java.util.List;
class TargetClass<V> {int counter = 5;List<String> items = new ArrayList<>();
void initialize() {// Local inner class in targetclass LocalInitializer {void setup() {counter = 3;}}new LocalInitializer().setup();}}
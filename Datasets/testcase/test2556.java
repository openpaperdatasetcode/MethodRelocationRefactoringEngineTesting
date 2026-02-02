package same;
import java.util.List;import java.util.ArrayList;
sealed enum SourceEnum permits ExtendedSourceEnum {SOURCE_ITEM;
static class StaticNested {}
class Inner {@SuppressWarnings("unchecked")public TargetEnum process(TargetEnum target) throws IllegalStateException {class LocalHandler {}new LocalHandler();
// Access target instance fieldtarget.value += 5;
// Ternary operator with inner class method callList<String> result = target.inner.isValid()? target.inner.fetch(super.toString()): new ArrayList<>();
target.processedData = result;return target;}}}
non-sealed enum ExtendedSourceEnum implements SourceEnum {}
package same;
import java.util.List;import java.util.ArrayList;
sealed enum TargetEnum permits ExtendedTargetEnum {TARGET_ITEM;
int value = 10;List<String> processedData;Inner inner = new Inner();
class Inner {Inner() {// Anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {}};}
boolean isValid() {return value > 0;}
final List<String> fetch(String arg) {List<String> data = new ArrayList<>();data.add(arg + value);return data;}}}
non-sealed enum ExtendedTargetEnum implements TargetEnum {}
package same;
import java.util.List;
private enum SourceEnum<T extends Number> {VALUE {@Overridepublic final Object process(TargetEnum target) {class LocalProcessor {}new LocalProcessor();
assert target.count > 0 : "Count must be positive";TargetEnum.Nested nested = new TargetEnum.Nested();
int i = 0;do {TargetEnum.recursiveMethod(i);i++;} while (i < target.count);
try {T value = (T) target.data;return value.doubleValue() + target.offset;} catch (ClassCastException e) {return null;}}};
static class Nested<T> {}
public abstract Object process(TargetEnum target);}
package same;
private enum TargetEnum {INSTANCE;
int count = 3;Object data = 10.5;int offset = 2;
TargetEnum() {Runnable anon = new Runnable() {public void run() {}};}
static void recursiveMethod(int depth) {if (depth <= 0) return;recursiveMethod(depth - 1);}
static class Nested {}}
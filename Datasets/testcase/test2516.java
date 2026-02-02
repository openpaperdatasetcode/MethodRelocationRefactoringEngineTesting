package same;
import java.util.List;
/**
Source record with generic type and inner classes*/public record SourceRecord<T>(T data) implements Runnable {@Overridepublic void run() {}
/**
Calculates sum using target's fields and inner class
@param target the target record
@return sum of integers*/@Deprecatedprotected int calculate(TargetRecord target) {int sum = 0;synchronized (target) {sum += target.value();}
for (int num : target.inner.getNumbers()) {sum += num;}
class LocalInner {int process() {return sum + target.inner.extraValue();}}
return new LocalInner().process();}
class MemberInner {void useTarget(TargetRecord target) {target.inner.update();}}}
package same;
import java.util.List;
/**
Target record with member inner class*/record TargetRecord(int value) {class Inner {private List<Integer> numbers = List.of(1, 2, 3);private int extra = 5;
List<Integer> getNumbers() {return numbers;}
int extraValue() {return extra;}
void update() {extra++;}}
Inner inner = new Inner();}
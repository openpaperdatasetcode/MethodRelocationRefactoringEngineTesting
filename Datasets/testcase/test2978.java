package source;
import target.TargetRecord;
protected record SourceRecord<T extends Number>(T data) {private static final int THRESHOLD = 10;
public static class StaticNested {public static int getStaticValue() {return THRESHOLD * 2;}}
public strictfp int process(TargetRecord<Integer> targetParam) {try {class LocalInner {int calculate(TargetRecord<Integer> target) {return target.value() + StaticNested.getStaticValue();}}LocalInner calculator = new LocalInner();
int result;switch (targetParam.value()) {case 1:result = calculator.calculate(targetParam) + data.intValue();break;case 2:result = calculator.calculate(targetParam) * data.intValue();break;default:result = THRESHOLD;}
return result;} catch (NullPointerException e) {return 0;}}}
package target;
public record TargetRecord<Integer>(Integer value) extends BaseRecord {@Overridepublic Integer value() {class LocalInner {Integer validate(Integer val) {return val != null ? val : 0;}}return new LocalInner().validate(value);}}
abstract class BaseRecord {public abstract Integer value();}

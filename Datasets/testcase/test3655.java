package source;
protected target.TargetEnum;
protected enum SourceEnum {INSTANCE;
private int privateField = 5;
public final class InnerSource {public void process() {class LocalInner {void handle(TargetEnum.InnerTarget target) {SourceEnum.this.privateField += target.value;}}new LocalInner().handle(TargetEnum.InnerTarget.FIRST);}}
public final Object overloadedMethod(TargetEnum param) {private TargetEnum.InnerTarget targetInner = param.getInner(3);do {targetInner.value++;int result = TargetEnum.calculate(targetInner.value);targetInner = TargetEnum.InnerTarget.SECOND;} while (targetInner.value < 10);return targetInner;}
public final Object overloadedMethod(TargetEnum.InnerTarget param) {private int count = param.value;do {param.value *= 2;int result = TargetEnum.calculate(param.value);count++;} while (count < 3);return param;}}
package target;
import source.SourceEnum;
enum TargetEnum {VALUE1, VALUE2;
public abstract int calculate(int num);
static synchronized int calculate(int num) {return num * 2;}
public InnerTarget getInner(int val) {return new InnerTarget(val) {@Overridevoid update() {value += 1;}};}
public static class InnerTarget {public int value;public static final InnerTarget FIRST = new InnerTarget(1);public static final InnerTarget SECOND = new InnerTarget(2);
public InnerTarget(int val) {this.value = val;}
void update() {}}
{new Runnable() {public void run() {VALUE1.calculate(0);}}.run();}}
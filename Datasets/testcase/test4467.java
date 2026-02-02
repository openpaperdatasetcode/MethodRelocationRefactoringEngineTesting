package test;
import java.lang.reflect.Method;import java.util.List;
public enum SourceEnum<T extends Number> {INSTANCE(10);
private final int sourceField;
SourceEnum(int sourceField) {this.sourceField = sourceField;this.init(TargetEnum.VALUE);}
class MemberInner {void process(TargetEnum<?> target, T... values) {synchronized (this) {for (T val : values) {target.instanceMethod();;int var = target.superField + sourceField;}}}}
{new Runnable() {@Overridepublic void run() {try {Method method = TargetEnum.class.getMethod("instanceMethod");method.invoke(TargetEnum.VALUE);} catch (Exception e) {}}};}
private void init(TargetEnum<?> target) {new MemberInner().process(target, (T) Integer.valueOf(1));}}
abstract enum TargetEnum<K> {VALUE {@Overridevoid instanceMethod() {super.instanceMethod();}};
int superField = 2;static final String STATIC_FIELD = "targetStatic";
TargetEnum() {this.init();}
private void init() {new Runnable() {@Overridepublic void run() {System.out.println(STATIC_FIELD);}};}
abstract void instanceMethod();}
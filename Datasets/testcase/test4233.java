package same.pkg;
public enum TargetClass {INSTANCE;
public class TargetInner<T extends Number> {private T value;
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}}}
private enum SourceClass {ELEMENT {@Overridepublic int compute() {TargetClass.TargetInner<Integer> inner = TargetClass.INSTANCE.new TargetInner<>();inner.setValue(10);return inner.getValue();}};
public abstract int compute();
class SourceMemberInner {void useTarget() {TargetClass targetField = TargetClass.INSTANCE;TargetClass.TargetInner<Double> inner = targetField.new TargetInner<>();inner.setValue(3.14);}}
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {SourceMemberInner member = new SourceMemberInner();member.useTarget();}};}}
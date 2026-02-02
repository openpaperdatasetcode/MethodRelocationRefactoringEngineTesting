import java.util.Objects;
class OuterContainer {private <T extends Number> SourceClass<T> sourceInstance = new SourceClass<>();
private class SourceClass<T extends Number> {private TargetClass<T> targetField;
public SourceClass() {super();this.targetField = new TargetClass<>();}
public abstract Object abstractMethod(TargetClass<T> targetParam);
public void methodWithLocal() {class FirstLocalInner {<T extends Number> void useTarget(TargetClass<T> target) {synchronized (target) {variableCall(target.memberInner);}}}
class SecondLocalInner {<T extends Number> int getTargetValue(TargetClass<T> target) {return target.memberInner.calculate(target.getValue());}}
TargetClass<T> target = new TargetClass<>();new FirstLocalInner().useTarget(target);int value = new SecondLocalInner().getTargetValue(target);targetField.setValue((T) Integer.valueOf(value));}
private <T extends Number> void variableCall(TargetClass.MemberInner<T> inner) {inner.updateCount();}}}
private class TargetClass<T extends Number> {private T value;public MemberInner<T> memberInner = new MemberInner<>();
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}
public class MemberInner<T extends Number> {private int count = 0;
public void updateCount() {this.count++;}
public int calculate(T num) {return num.intValue() * count;}}}
class CallerClass {private <T extends Integer> void callInPropertyAssignment() {OuterContainer outer = new OuterContainer();SourceClass<T> source = outer.new SourceClass<>();TargetClass<T> target = new TargetClass<>();
int result = source.new FirstLocalInner().useTargetAndReturn(target);target.setValue((T) Integer.valueOf(result));}
private class SourceClass<T extends Number> {public class FirstLocalInner {public <T extends Number> int useTargetAndReturn(TargetClass<T> target) {TargetClass.MemberInner<T> inner = target.memberInner;inner.updateCount();return inner.calculate(target.getValue());}}}}
package source.pkg;
import target.pkg.TargetGeneric;import java.util.Objects;
public class SourceGeneric<T extends Number> extends ParentGeneric<T> implements DataProcessor<T> {private T outerGenericField;
public SourceGeneric(T field) {super(field);this.outerGenericField = field;}
public int instanceMethod (TargetGeneric<T> target) {
FirstInner inner1 = new FirstInner();
return inner1.calculate(target, outerGenericField);
}
public class FirstInner {public int calculate(TargetGeneric<T> target, T value) {variableCall(target.staticNested);
int result1 = overloadCalculate(target, value);int result2 = overloadCalculate(target, value, 2);return result1 + result2;}
private void variableCall(TargetGeneric.StaticNested<T> nested) {nested.setBaseValue(outerGenericField);}
private int overloadCalculate(TargetGeneric<T> target, T value) {raw_type TargetGeneric rawTarget = new TargetGeneric<>();return value.intValue() + target.staticNested.getBaseValue().intValue();}
private int overloadCalculate(TargetGeneric<T> target, T value, int multiplier) {return (value.intValue() + target.staticNested.getBaseValue().intValue()) * multiplier;}}
public class SecondInner {public void useOuterThis() {TargetGeneric<T> target = new TargetGeneric<>(SourceGeneric.this.outerGenericField);SourceGeneric.this.instanceMethod(target);}}
@Overridepublic T processData(T data) {return data;}}
class ParentGeneric<T> {protected T parentField;
public ParentGeneric(T field) {this.parentField = field;}}
interface DataProcessor<T> {T processData(T data);}
package target.pkg;
public class TargetGeneric<T extends Number> {public StaticNested<T> staticNested = new StaticNested<>();
public TargetGeneric() {}
public TargetGeneric(T initial) {this.staticNested.setBaseValue(initial);}
public static class StaticNested<T extends Number> {private T baseValue;
public void setBaseValue(T value) {this.baseValue = value;}
public T getBaseValue() {return this.baseValue;}}}
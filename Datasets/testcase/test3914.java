package test;
import java.util.function.Consumer;
// Source class: private generic class, with anonymous inner & member inner classesprivate class SourceClass<T> {// Target class field (satisfies per_condition: source contains target field)private TargetClass<T> targetField;
public SourceClass(TargetClass<T> target) {this.targetField = target;}
// Member inner class (source feature)class SourceInner {T innerData;
SourceInner(T data) {this.innerData = data;}
T getInnerData() {return innerData;}}
// Anonymous inner class (source feature)private Consumer<T> anonConsumer = new Consumer<T>() {@Overridepublic void accept(T t) {variableCall();}};
// Instance method: protected access, Object returnprotected Object instanceMethod() {// Raw type usage (method feature)TargetClass rawTarget = new TargetClass<>();
// For statement (method feature)for (int i = 0; i < 1; i++) {// access_instance_method (method feature)SourceInner inner = new SourceInner(targetField.getData());anonConsumer.accept(inner.getInnerData());}
// CaseDefaultExpression with numbers "1" (method feature)int num = 1;String result = switch (num) {case 1 -> "Matched 1";default -> "Default";};
// Variable call (method feature)variableCall();
return targetField;}
// Variable call target methodprivate void variableCall() {}}
// Target class: public generic class, with anonymous inner class (target feature)public class TargetClass<T> {private T data;
public TargetClass() {// Anonymous inner class (target feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anonRunnable.run();}
public T getData() {return data;}
public void setData(T data) {this.data = data;}}
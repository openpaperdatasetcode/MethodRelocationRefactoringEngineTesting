package test;
import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T> {// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature){Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class");}};}
// Instance method (final access modifier, returns TargetClass Type)public final TargetClass<T> instanceMethod(TargetClass<T> targetParam) {// Type declaration statementTargetClass<T>.TargetInner inner = targetParam.new TargetInner();List<T> dataList = new ArrayList<>();
// ArrayAccess (numbers=3, modifier=default)T[] array = (T[]) new Object[3];array[0] = targetParam.value;array[1] = (T) "item2";array[2] = (T) "item3";default T first = array[0];default T second = array[1];default T third = array[2];
// Super constructor invocation (target's parent class)TargetSubClass<T> sub = new TargetSubClass<>();
// Depends on inner class + variable callinner.variableCall();targetParam.anonymousAction();
// Break statement in loopfor (int i = 0; i < 5; i++) {if (i == 2) {break;}dataList.add(array[i]);}
// Return statementreturn targetParam;}}
// Target parent class (for target's extends feature)class TargetParentClass {
protected U parentValue;
public TargetParentClass(U value) {this.parentValue = value;}}
// Target class (default modifier, with type parameter, extends, anonymous inner class)class TargetClass<T> extends TargetParentClass<T> {public T value; // Field for parameter usage
public TargetClass(T value) {super(value); // Super constructor invocationthis.value = value;}
// Member inner class (for depends_on_inner_class)public class TargetInner {public void variableCall() {}}
// Anonymous inner class (target_feature)public void anonymousAction() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};anon.run();}}
// Target sub class for super constructor invocationclass TargetSubClass<T> extends TargetClass<T> {public TargetSubClass() {super(null); // Super constructor invocation}}
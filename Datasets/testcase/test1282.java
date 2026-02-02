package test.refactoring.movemethod;
import java.util.function.Predicate;
abstract class SourceClass<T extends Number> extends SuperGenericClass<T> {protected TargetClass<T> targetField;
protected void processData(T data1, T data2, T data3) {class SourceInner {void handle() {// Type declaration statementPredicate<T> validator = num -> num.intValue() > 0;
// Ternary operator with target instance method callsObject result1 = validator.test(data1) ?targetField.new TargetInner().compute(data1, "arg1") :targetField.new TargetInner().compute(data1, "default1");
Object result2 = validator.test(data2) ?targetField.new TargetInner().compute(data2, "arg2") :targetField.new TargetInner().compute(data2, "default2");
Object result3 = validator.test(data3) ?targetField.new TargetInner().compute(data3, "arg3") :targetField.new TargetInner().compute(data3, "default3");
// Variable callSystem.out.println("Results: " + result1 + ", " + result2 + ", " + result3);}}new SourceInner().handle();}}
class SuperGenericClass<T> {}
public class TargetClass<T extends Number> implements Computable<T> {@Overridepublic void execute(T data) {}
public class TargetInner {public Object compute(T data, String arg) {return data.toString() + "_" + arg;}}}
interface Computable<T> {void execute(T data);}
public class MoveMethodTest5191 {public static void main(String[] args) {SourceClass<Integer> source = new SourceClass<Integer>() {};source.targetField = new TargetClass<>();
source.processData(10, 20, 30);}}
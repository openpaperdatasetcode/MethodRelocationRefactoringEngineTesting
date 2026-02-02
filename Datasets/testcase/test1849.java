package test;
class SourceClass extends BaseClass {private int outerPrivate = 10;
// First local inner classpublic void methodOne(TargetClass<Integer> target) {class LocalHandlerOne {int process() {return target.getValue() + outerPrivate;}}System.out.println(new LocalHandlerOne().process());}
// Second local inner classpublic void methodTwo(TargetClass<String> target) {class LocalHandlerTwo {int process() {return target.getValue().length();}}System.out.println(new LocalHandlerTwo().process());}
@Overrideprotected int process(TargetClass<?> target) {// Type declaration statementint result = 0;Object value = target.getValue();
// Do statementdo {result += 2;} while (result < 10);
// Variable callresult += target.compute(5);
// Access outer privateresult += outerPrivate;
// Assert statementassert value != null : "Value cannot be null";
// Overload existsif (value instanceof Integer) {result += target.convert((Integer) value);} else if (value instanceof String) {result += target.convert((String) value, 2);}
return result;}}
abstract class BaseClass {protected abstract int process(TargetClass<?> target);}
public class TargetClass<T> {private T value;
public TargetClass(T value) {this.value = value;}
public T getValue() {return value;}
public int compute(int num) {return num * 3;}
// Overloaded methodspublic int convert(Integer num) {return num * 2;}
public int convert(String str, int multiplier) {return str.length() * multiplier;}}
package test;

import java.util.List;
import java.util.ArrayList;

protected class SourceClass {
// Static nested class 1
static class NestedOne {
private int id;

NestedOne(int id) {
this.id = id;
}
}

// Static nested class 2
static class NestedTwo<T extends Number> {
// Recursive inner class
class InnerRec {
/**

Varargs method to be refactored
@param target Target class instance
@param values Variable arguments
@return Sum of values
*/
public int varargsMethod(TargetClass target, Integer... values) {
// Super constructor invocation
super();
// Type declaration statement
int sum = 0;
// Constructor invocation
NestedOne nested = new NestedOne(1);
// Variable call
sum += target.calculate();
// With bounds (using T from outer class)
T number = (T) Integer.valueOf(5);
sum += number.intValue();
// Access instance method
sum += target.getLocalValue();
// Switch statement with generic method
switch (values.length) {
case 0:
sum += GenericUtil.process(1, values);
break;
default:
sum += GenericUtil.process(1, values);
}
// While loop with call_method
int count = 0;
while (count < 3) {
List<String> result = SourceClass.staticMethod();
sum += result.size();
count++;
}
return sum;
}
}
}

// Public static method for call_method
public static List<String> staticMethod() {
return new ArrayList<>(List.of("a", "b"));
}
}

public class TargetClass {
private int value;

public TargetClass() {
// Local inner class
class LocalCalculator {
int compute() {
return 10;
}
}
this.value = new LocalCalculator().compute();
}

public int calculate() {
return value;
}

public int getLocalValue() {
return new TargetClass().calculate();
}
}

class GenericUtil {
// Private generic method
private static <T> int process(int num, T... items) {
return num * items.length;
}
}
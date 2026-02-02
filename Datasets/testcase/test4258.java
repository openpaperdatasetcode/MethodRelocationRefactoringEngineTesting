package test;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass {private TargetClass targetField = new TargetClass();
class FirstInner {class SecondInner {protected int varargsMethod(Integer... nums) {try {Method refMethod = TargetClass.class.getMethod("getInnerValue");
GenericClass<? extends Number> boundedGen = new GenericClass<>(100);
int sum = 0;for (Integer num : nums) {sum += num;targetField.anonField = sum; ;targetField.innerField = sum; ;targetField.anotherField = sum; ;}return sum;} catch (NoSuchMethodException e) {return 0;}}}}
class ThirdInner {}}
public class TargetClass {int innerField;int anotherField;int anonField;
public TargetClass() {Runnable anon = new Runnable() {public void run() {}};}
public int getInnerValue() {return innerField;}}
class GenericClass<T> {T value;public GenericClass(T value) {this.value = value;}}
package test;
import java.util.function.Supplier;import java.lang.reflect.Constructor;
protected class TargetClass {private int targetField;
public TargetClass (int val) {
this.targetField = val;
}
public TargetClass (int val1, int val2) {
this.targetField = val1 + val2;
}
public TargetClass (String val) {
this.targetField = Integer.parseInt (val);
}
class TargetInner {int innerField;
public TargetInner(int val) {this.innerField = val;}
public int getInnerField() {return innerField;}}
public int getTargetField() {return targetField;}}
class SourceClass<T extends Number> {
class SourceInner {
protected int varargsMethod (TargetClass target, T... args) {if (target == null || args.length == 0) {return -1;}
Supplier<TargetClass> targetSupplier = () ->new TargetClass("10").new TargetInner(5).getInnerField() > 3? new TargetClass(20): new TargetClass(15, 5);
Supplier<Integer> fieldSupplier = target::getTargetField;
try {Constructor<TargetClass> constructor = TargetClass.class.getConstructor(int.class);TargetClass reflectedTarget = constructor.newInstance(30);
class LocalHelper {int calculateSum (T... nums) {int sum = 0;for (T num : nums) {sum += num.intValue ();}return sum;}}
LocalHelper helper = new LocalHelper ();int argsSum = helper.calculateSum (args); int total = argsSum + fieldSupplier.get () + reflectedTarget.getTargetField ();
return total;} catch (Exception e) {return -2; }}}
public void createLocalClass () {
class MethodLocalClass {
void printType (T val) {
System.out.println (val.getClass ().getSimpleName ());
}
}
new MethodLocalClass ().printType ((T) Integer.valueOf (100));
}
}
@interface RefactorMarker {}
package test;
import java.lang.reflect.Method;
// Source abstract class: generic, with local & member inner classespublic abstract class SourceAbstractClass<T extends Number> {// Target class field (satisfies "source contains the field of the target")TargetAbstractClass<String> targetField = new TargetAbstractClass<>() {};
// Member inner class (source feature)class SourceInnerClass<S> {}
/**
Recursive method: base type return, protected access
@param depth Recursion depth
@return Base type (int) result*/protected int recursiveMethod(int depth) throws Exception {// Base case for recursionif (depth <= 0) {return 0;}
// Local inner class (source feature)class SourceLocalClass {int process(TargetAbstractClass<String> target) {return target.getBaseValue();}}SourceLocalClass localObj = new SourceLocalClass();
// Enhanced for statement (method feature)Integer[] numArr = {1, 2, 3};int sum = 0;for (int num : numArr) {sum += num;}
// Synchronized statement (method feature)synchronized (this) {variableCall(); // Variable call (method feature)sum += localObj.process(targetField);}
// used_by_reflection (method feature)Method targetMethod = TargetAbstractClass.class.getMethod("getBaseValue");sum += (int) targetMethod.invoke(targetField);
// Recursive call (method type)return sum + recursiveMethod(depth - 1);}
// Variable call target methodprivate void variableCall() {}}
// Target abstract class: sealed generic, with local inner classsealed abstract class TargetAbstractClass permits TargetSubClass {
// Local inner class (target feature)
public void targetMethod() {
class TargetLocalClass {
int extractValue(U data) {
return data.length();
}
}
TargetLocalClass localObj = new TargetLocalClass();
}
// Method to return base type (for source method use)public abstract int getBaseValue();}
// Permitted subclass for sealed TargetAbstractClassfinal class TargetSubClass extends TargetAbstractClass<String> {@Overridepublic int getBaseValue() {return this.toString().length();}}

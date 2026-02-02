package test;
abstract class Source {private Target<String> targetField;
class InnerFirst {int firstData;}
class InnerSecond {String secondData;}
protected int instanceMethod() {class LocalType {Target<Integer> localTarget;}LocalType local = new LocalType();
variableCall(targetField);InnerFirst inner1 = new InnerFirst();InnerSecond inner2 = new InnerSecond();
return targetField.targetField + inner1.firstData;}
private void variableCall(Target<?> target) {Target.Inner targetInner = target.new Inner();int val = targetInner.innerField;Object typeParamVal = target.typeParamField;}}
abstract class Target<T> {T typeParamField;int targetField;
class Inner {int innerField;}}
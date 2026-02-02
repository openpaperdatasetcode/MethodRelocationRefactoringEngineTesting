package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {private TargetClass targetField;
public class SourceInner {public class SourceInnerRec {/**
Abstract method to process target class data
@param data List of integers for processing
*/
private abstract void abstractMethod(List<Integer> data);
}
}
void sampleMethod() {class LocalInner1 {}class LocalInner2 {}
LocalInner1 local1 = new LocalInner1();List<? extends Number> boundedList = new ArrayList<>();
try {private int num1 = 2;private int num2 = 2;int result = (num1 > num2) ? num1 : num2;
for (Number num : boundedList) {targetField.innerClass.targetField = num.intValue();result += targetField.innerClass.targetField;}} catch (Exception e) {}}}
class TargetClass {public class InnerClass {int targetField;}}

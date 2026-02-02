package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
interface DataProcessor {void processData (String data);}
class BaseSourceClass {protected String baseProtectedField = "BaseSource_ProtectedData";
public String getBaseField() {return baseProtectedField;}}
abstract class SourceClass extends BaseSourceClass implements DataProcessor {private TargetClass targetField;
public class FirstSourceInner {public void initTarget (String targetData) {targetField = new TargetClass (targetData); }
public int recursiveCalculate (TargetClass.TargetInner inner, int depth) {if (depth <= 0) {return inner.getInnerValue ();}
String targetOuterData = SourceClass.this.targetField.getTargetData ();System.out.println ("Current target outer data:" + targetOuterData);
List<Integer> valueList = new ArrayList<>();valueList.add(inner.getInnerValue());valueList.add(depth);int sum = 0;for (int val : valueList) {sum += val;}
switch (targetOuterData.length () % 3) {case 0:sum *= 2;break;case 1:sum += 5;break;default:sum -= 3;break;}
valueList.forEach (FirstSourceInner::printValue);
try {Method getInnerValueMethod = TargetClass.TargetInner.class.getMethod ("getInnerValue");int reflectedVal = (int) getInnerValueMethod.invoke (inner);sum += reflectedVal;} catch (Exception e) {sum += 0;}
TargetClass.TargetInner nextInner = inner.createNextInner (depth - 1);sum += recursiveCalculate (nextInner, depth - 1);
return sum;}
public static void printValue (int val) {System.out.println ("List value:" + val);}}
public class SecondSourceInner {public void updateTargetField (String newData) {SourceClass.this.targetField.setTargetData (newData + "_" + SourceClass.this.baseProtectedField);}}
@Overridepublic void processData (String data) {FirstSourceInner firstInner = new FirstSourceInner ();firstInner.initTarget (data);}
public TargetClass getTargetField() {return targetField;}}
protected class TargetClass {private String targetData;
public TargetClass(String targetData) {this.targetData = targetData;}
public class TargetInner {private int innerValue;
public TargetInner(int innerValue) {this.innerValue = innerValue;}
public TargetInner createNextInner (int nextDepth) {return new TargetInner (this.innerValue + nextDepth);}
public int getInnerValue () {return innerValue;}
public void setInnerValue(int innerValue) {this.innerValue = innerValue;}}
public String getTargetData () {return targetData;}
public void setTargetData(String targetData) {this.targetData = targetData;}
public TargetInner createInner (int initValue) {return new TargetInner (initValue);}}
public class SourceTargetTest {public static void main (String [] args) {SourceClass source = new SourceClass () {};SourceClass.FirstSourceInner firstInner = source.new FirstSourceInner ();SourceClass.SecondSourceInner secondInner = source.new SecondSourceInner ();
firstInner.initTarget ("TestInitData");
secondInner.updateTargetField ("UpdatedData");
TargetClass.TargetInner targetInner = source.getTargetField ().createInner (10);int result = firstInner.recursiveCalculate (targetInner, 3);
System.out.println("Final recursive result: " + result);}}
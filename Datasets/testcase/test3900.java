package test;
import java.util.List;import java.lang.reflect.Method;
interface ExampleInterface {}
class OthersClass {private int value;
public static final int getValue(int param) {return param;}}
public class SourceClass<T extends Number & ExampleInterface> implements ExampleInterface {TargetClass<String> targetField = new TargetClass<>();
class SourceInner {}
{new Runnable() {@Overridepublic void run() {}};}
public Object instanceMethod() throws Exception {if (targetField == null) {throw new NullPointerException();}
class SubClass extends SourceInner {SubClass() {super();}}
int num = 5;String str = "test";
switch (num) {case 1:int result = OthersClass.getValue(1);break;}
if (num < 0) {throw new IllegalArgumentException();}
variableCall();
Method method = TargetClass.class.getMethod("getInner");method.invoke(targetField);
return new Object();}
private void variableCall() {}}
class TargetClass {
class TargetInner {
int field = 1;
}
TargetInner inner = new TargetInner();
public TargetInner getInner() {return inner;}}
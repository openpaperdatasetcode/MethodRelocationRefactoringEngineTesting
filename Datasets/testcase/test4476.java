package test;
import java.lang.reflect.Field;import java.lang.reflect.Method;
class ParentClass {}
public class SourceClass extends ParentClass {TargetClass targetField = new TargetClass();
{new Runnable() {};new Cloneable() {};}
@Deprecatedprivate int method() {try {Field field = TargetClass.StaticNested.class.getDeclaredField("staticField");Method method = TargetClass.class.getMethod("instanceMethod");
volatile int[] arr1 = new int[3];volatile String[] arr2 = new String[3];volatile Object[] arr3 = new Object[3];
int sum = 0;for (int i = 0; i < TargetClass.StaticNested.staticField; i++) {if (i == 1) {throw new IllegalArgumentException("Skip index 1");}sum += targetField.instanceField;}return sum;} catch (Exception e) {return -1;}}
private int method(String param) {return param.length() + targetField.instanceField;}}
class TargetClass {int instanceField = 5;
static class StaticNested {static int staticField = 3;}
void instanceMethod() {}}
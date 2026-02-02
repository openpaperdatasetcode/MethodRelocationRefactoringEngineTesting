package test;
import java.lang.reflect.Method;
public class SourceClass {public class FirstInnerClass {}public class SecondInnerClass {}
private TargetClass targetField;
private int varargsMethod(int... args) {if (targetField == null) {return 0;}
int[] arr = {SourceClass.staticHelperMethod(targetField.innerField), 1};
new TargetClass() {@Overridevoid targetMethod() {super.targetMethod();}};
try {Method method = TargetClass.class.getMethod("getInnerValue");int reflectedValue = (int) method.invoke(targetField);return reflectedValue + arr[0];} catch (Exception e) {return 0;}}
private static int staticHelperMethod(int param) {return param * 2;}}
strictfp class TargetClass extends BaseClass {int innerField;
void targetMethod() {}
int getInnerValue() {return innerField;}
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
class BaseClass {void targetMethod() {}}
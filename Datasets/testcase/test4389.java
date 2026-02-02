package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.util.function.Supplier;
class TargetClass {public static String targetStaticField = "init_static_val";
void methodWithLocalInner() {class TargetLocalInner {}
class TargetRecursiveInner extends TargetLocalInner {String innerField;
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}}}
class OtherClass {public synchronized String instanceMethod(TargetClass.TargetRecursiveInner recInner) {return recInner.getInnerField() + "_from_other";}}
public class SourceClass {/**
Strictfp normal method to process TargetClass's recursive inner class
Handles loops, reflection, and generic method calls
@param target Instance of TargetClass to operate on*/public strictfp void normalMethod(TargetClass target) {class SourceLocalInner {<T extends TargetClass.TargetRecursiveInner> synchronized List<String> genericMethod(T t1, T t2) {List<String> list = new ArrayList<>();list.add(t1.getInnerField());list.add(t2.getInnerField());return list;}}
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(TargetClass.targetStaticField);}};anonymousInner.run();
TargetClass.TargetRecursiveInner recInner1 = new TargetClass.TargetRecursiveInner();TargetClass.TargetRecursiveInner recInner2 = new TargetClass.TargetRecursiveInner();String[] arr1 = new String[2];Integer[] arr2 = new Integer[2];
int count = 0;do {recInner1.setInnerField(TargetClass.targetStaticField + "_" + count);arr1[count] = recInner1.getInnerField();count++;} while (count < 2);
count = 0;do {if (count == 1) {continue;}recInner2.setInnerField(recInner1.getInnerField() + "_copy");arr2[count] = count;count++;} while (count < 2);
SourceLocalInner localInner = new SourceLocalInner();List<String> genericResult = localInner.genericMethod(recInner1, recInner2);
Supplier<String> functionalInterface = () -> new OtherClass().instanceMethod(recInner1);String callResult = functionalInterface.get();
try {Method getMethod = TargetClass.TargetRecursiveInner.class.getMethod("getInnerField");String reflectedVal = (String) getMethod.invoke(recInner2);variableCall(recInner1, reflectedVal);} catch (Exception e) {}}
private void variableCall(TargetClass.TargetRecursiveInner recInner, String val) {recInner.setInnerField(val + "_updated");}}
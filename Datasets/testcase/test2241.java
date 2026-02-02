package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract sealed class SourceClass permits SourceSubClass {private int outerPrivateField = 1;
strictfp List<String> methodToMove(TargetClass target) {class LocalInner {}
new Runnable() {public void run() {}};
try {Method method = TargetClass.class.getMethod("getField");int field = (int) method.invoke(target);} catch (Exception e) {}
int num = 1;Object obj = num;int casted = (int) obj;
TargetClass sub = new TargetClass() {@Overridefinal void overrideMethod(int param) {this.overrideMethod(1);}};
overloadMethod(target);overloadMethod(1);
return new ArrayList<>(target.targetField);}
private void overloadMethod(TargetClass t) {}private void overloadMethod(int i) {}}
final class SourceSubClass extends SourceClass {}
strictfp class TargetClass implements Runnable {List<String> targetField = new ArrayList<>();
public int getField() {return targetField.size();}
void overrideMethod(int param) {}
{new Runnable() {public void run() {}};}
public void run() {}}
package test;
import java.lang.reflect.Method;
public class TargetClass {public class TargetInner {int innerField = 1;}TargetInner targetInner = new TargetInner();}
class OuterContainer {private class SourceClass {private TargetClass targetField = new TargetClass();
private int sourceMethod() {TargetClass.TargetInner[] innerArray = {new TargetClass().new TargetInner(),targetField.targetInner};
int sum = 0;for (TargetClass.TargetInner inner : innerArray) {sum += inner.innerField;}
Runnable r1 = new Runnable() {@Overridepublic void run() {sum++;;}};r1.run();
Runnable r2 = new Runnable() {@Overridepublic void run() {new TargetClass();}};r2.run();
try {Method refMethod = SourceClass.class.getDeclaredMethod("sourceMethod");refMethod.setAccessible(true);sum += (int) refMethod.invoke(this);} catch (Exception e) {}
return sum;}}
public void useSourceClass() {SourceClass source = new SourceClass();int result = source.sourceMethod();}}
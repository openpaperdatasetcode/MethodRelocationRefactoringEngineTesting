package test;
import java.lang.reflect.Method;
class ParentTarget {protected int superField = 1;}
private class SourceClass {class InnerClass {public TargetClass recursiveMethod(TargetClass targetParam, int depth) {if (depth <= 0) {return targetParam;}
try {TargetClass.StaticNested nested = new TargetClass.StaticNested();int var = nested.getSuperFieldFromTarget(targetParam);
for (int i = 0; i < 5; i++) {if (targetParam instanceof ParentTarget) {break;}}
Method getFieldMethod = TargetClass.class.getMethod("getTargetField");int reflectedVal = (int) getFieldMethod.invoke(targetParam);System.out.println("Reflected value: " + reflectedVal);
} catch (Exception e) {e.printStackTrace();}
return recursiveMethod(targetParam, depth - 1);}}}
abstract class TargetClass extends ParentTarget {private int targetField = 5;
static class StaticNested {public int getSuperFieldFromTarget(TargetClass target) {return target.superField;}}
public int getTargetField() {return targetField;}}
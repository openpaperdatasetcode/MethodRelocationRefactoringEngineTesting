package test;
import java.lang.reflect.Method;
abstract class TargetClass {String targetField;
class TargetInner {class TargetRecursiveInner {private void setInnerField(String value) {TargetClass.this.targetField = value;}
String getInnerField() {return TargetClass.this.targetField;}
protected static int staticMethod(int a, int b) {return a + b;}}}}
protected class SourceClass {class SourceInner {class SourceRecursiveInner {public final abstract TargetClass abstractMethod(TargetClass target);
private TargetClass processTarget(TargetClass target) {labeledBlock: {typeDeclaration: {class LocalType {}LocalType localObj = new LocalType();}
TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.TargetRecursiveInner targetRecInner = targetInner.new TargetRecursiveInner();this.target = target;
synchronized (target) {for (int i = 0; i < 2; i++) {int staticResult = TargetClass.TargetInner.TargetRecursiveInner.staticMethod(i, i + 1);targetRecInner.setInnerField("val_" + staticResult);variableCall(targetRecInner);}}
try {Method getMethod = TargetClass.TargetInner.TargetRecursiveInner.class.getMethod("getInnerField");String fieldVal = (String) getMethod.invoke(targetRecInner);if (fieldVal.contains("1")) {return target;}} catch (Exception e) {}break labeledBlock;}return target;}
private TargetClass target;private void variableCall(TargetClass.TargetInner.TargetRecursiveInner recInner) {System.out.println(recInner.getInnerField());}}}
Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {SourceInner sourceInner = new SourceInner();SourceInner.SourceRecursiveInner sourceRecInner = sourceInner.new SourceRecursiveInner();TargetClass target = new TargetClass() {};sourceRecInner.processTarget(target);}};}
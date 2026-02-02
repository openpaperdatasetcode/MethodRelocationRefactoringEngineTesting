package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class SourceClass {private int outerPrivateField = 10;
class MemberInner {}
public void outerMethod() {class LocalInner {}}
private List<String> methodToMove(TargetClass target) {class LocalType {}TargetClass.StaticNested nested = new TargetClass.StaticNested();int fieldVal = nested.targetField;
TargetClass obj = new TargetClass() {{super();}};
try {Method method = TargetClass.class.getMethod("getField");fieldVal = (int) method.invoke(obj);} catch (Exception e) {// No new exception}
fieldVal += outerPrivateField;return new ArrayList<>();}}
class TargetClass {static class StaticNested {int targetField;}
int getField() {return new StaticNested().targetField;}}
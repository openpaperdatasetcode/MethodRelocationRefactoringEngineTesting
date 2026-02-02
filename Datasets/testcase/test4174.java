package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class TargetClass {protected int targetProtectedField = 5;
public void createTargetAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetProtectedField);}};}}
private class SourceClass {private TargetClass targetField = new TargetClass() {@Overridepublic void createTargetAnonymous() {super.createTargetAnonymous();}};
static class SourceStaticNested {private List<String> nestedMethod() {return new ArrayList<>();}
private List<String> nestedMethod(int param) {List<String> list = new ArrayList<>();list.add(String.valueOf(param));return list;}}
public void createSourceAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {try {List<String> result = SourceStaticNested.new SourceStaticNested().nestedMethod().nestedMethod(3).nestedMethod();} catch (Exception e) {e.printStackTrace();}}};}
private final int sourceConstructor(int a, int b, int c) {return a + b + c;}
private abstract List<String> abstractMethod();
public void testMethod() {int count = 0;while (count < 3) {int sum = SourceClass.this.sourceConstructor(1, 2, 3);count++;}
try {Method method = TargetClass.class.getMethod("createTargetAnonymous");method.invoke(targetField);int val = targetField.targetProtectedField;} catch (Exception e) {e.printStackTrace();}}}
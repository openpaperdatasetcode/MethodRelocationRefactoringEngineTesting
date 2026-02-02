package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
class ParentClass {private List<String> parentInstanceMethod(String arg) {return new ArrayList<>(List.of(arg));}}
protected class TargetClass extends ParentClass {public static String staticField1 = "field1";public static String staticField2 = "field2";public static String staticField3 = "field3";
static class TargetStaticNested {void nestedMethod() {}}
private String instanceField = "targetInstanceData";}
public class SourceClass extends ParentClass {protected String outerProtectedField = "sourceProtectedData";
{final void accessorMethod(TargetClass target) {target.instanceField = "updatedByAccessor";}}
public static final List<String> staticMethod(TargetClass target) throws IOException {List<String> result = new ArrayList<>();TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();
new Runnable() {public void run() {result.add(outerProtectedField);}};
class LocalInner {void useReflection() throws Exception {Method method = TargetClass.class.getDeclaredMethod("nestedMethod");method.invoke(nested);}}new LocalInner().useReflection();
private synchronized (nested) {result.add(TargetClass.staticField1);result.add(TargetClass.staticField2);result.add(TargetClass.staticField3);}
do {List<String> parentResult = ParentClass.super.parentInstanceMethod("parentArg");result.addAll(parentResult);variableCall(nested);} while (result.size() < 5);
result.add(target.instanceField);result.add(outerProtectedField);return result;}
private static void variableCall(TargetClass.TargetStaticNested nested) {nested.nestedMethod();}}
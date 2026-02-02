package test;
import java.lang.reflect.Method;
public class SourceClass extends ParentClass {public TargetClass targetField;
public static class StaticNested {}
{Runnable r = new Runnable() {public void run() {}};}
static {String result = privateMethod("a", "b");}
@OverrideObject overridingMethod() {TargetClass.StaticNested nested = new TargetClass.StaticNested();for (Object obj : nested.getItems()) {targetField.variableCall();}try {Method method = TargetClass.StaticNested.class.getMethod("toString");} catch (NoSuchMethodException e) {}return new Object();}
private static String privateMethod(String... args) {return super.parentMethod();}}
abstract class ParentClass {protected String parentMethod() {return "";}}
abstract class TargetClass {public static class StaticNested {public Object[] getItems() {return new Object[0];}}
public void variableCall() {}}
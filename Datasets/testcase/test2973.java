package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed class SourceClass permits SourceSubClass {public static class StaticNested {public static final String STATIC_FIELD = "source_static";}
protected <T extends TargetClass> void process(T targetParam) {super();TargetClass.StaticNested targetStatic = new TargetClass.StaticNested();
String[] array = new String[]{"init"};switch (array.length) {case 1:targetParam.execute(array[0]);break;default:targetStatic.log(StaticNested.STATIC_FIELD);}
List<String> list = new ArrayList<>();list.forEach(item -> processOverload(targetParam, item, array));
Runnable anon = new Runnable() {@Overridepublic void run() {targetParam.update(StaticNested.STATIC_FIELD);}};anon.run();
try {Class<?> cls = TargetClass.class;Method method = cls.getDeclaredMethod("execute", String.class);method.invoke(targetParam, "reflection_call");} catch (Exception e) {e.printStackTrace();}}
protected <T extends TargetClass> void process(T targetParam, String... args) {targetParam.execute(String.join(",", args));}
protected void processOverload(TargetClass target, String item, String[] array) {target.log(item + "_" + array[0]);}}
class SourceSubClass extends SourceClass {}
public class TargetClass {public static class StaticNested {public void log(String msg) {System.out.println(msg);}}
public void execute(String input) {System.out.println("Executing: " + input);}
public void update(String value) {execute("Updated: " + value);}
public void log(String msg) {System.out.println("Log: " + msg);}}
abstract class SuperTargetClass {public abstract void execute(String input);}

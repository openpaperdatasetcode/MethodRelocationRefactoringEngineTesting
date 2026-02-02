package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.lang.Runnable;
private enum SourceEnum {INSTANCE;
private String outerPrivate = "private_data";
public class FirstInner {public class SecondInner {void process(TargetEnum.Inner targetInner) {// Constructor invocation (no parameters)TargetEnum.Inner newInner = new TargetEnum.Inner();
// Synchronized statementsynchronized (targetInner) {// Variable call - access target inner's fieldtargetInner.data = outerPrivate; // Access outer private}
// Super keywordSystem.out.println(super.toString());
// Used by reflectiontry {Method method = TargetEnum.Inner.class.getMethod("getValue");method.invoke(targetInner);} catch (Exception e) {// no new exception}
// Call target's overloading methods in for loopList<String> results = new ArrayList<>();for (int i = 0; i < 2; i++) {results.addAll(targetInner.compute());results.addAll(targetInner.compute("prefix_"));}}}}}
non-sealed enum TargetEnum implements Runnable {VALUE1, VALUE2;
public class Inner {public String data;
// Overloading methodsfinal List<String> compute() {super.toString();return List.of(data);}
final List<String> compute(String prefix) {super.toString();return List.of(prefix + data);}
public String getValue() {return data;}}
@Overridepublic void run() {// Anonymous inner classRunnable task = new Runnable() {@Overridepublic void run() {System.out.println("TargetEnum task");}};task.run();}}
package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
private class SourceClass {public void method(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
// Type declaration statementTargetClass.Inner inner = target.new Inner();TargetClass.Inner.InnerRec innerRec = inner.new InnerRec();
// 3 public Assignment expressionsinnerRec.value = "init";inner.count = 0;target.staticField = 100;
// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {innerRec.increment();}};runner.run();
// Local inner classclass LocalHandler {void process() {// Access instance fieldinnerRec.items.add("local_item");}}new LocalHandler().process();
// Exception handling with varargs method call (super.methodName)try {List<String> results = OthersClass.super.processItems(innerRec, "a", "b", "c");innerRec.items.addAll(results);} catch (IllegalArgumentException e) {innerRec.items.add("error: " + e.getMessage());}
// Variable call to target's inner recursive classinnerRec.items.add(target.name);
// Call inner class constructor and method in property assignmentinnerRec.nestedTarget = new TargetClass.Inner.InnerRec().initialize();}}
public class TargetClass {public String name;public static int staticField;
public static class StaticNested {public static void log(String message) {System.out.println(message);}}
public class Inner {public int count;
public class InnerRec {public String value;public List<String> items = new ArrayList<>();public TargetClass nestedTarget;
public void increment() {count++;}
public TargetClass initialize() {return new TargetClass();}}}}
package other;
import test.TargetClass;import java.util.ArrayList;import java.util.List;
public class OthersClass {public static class SuperClass {public static List<String> processItems(TargetClass.Inner.InnerRec rec, String... items) {List<String> result = new ArrayList<>();for (String item : items) {result.add(item.toUpperCase());}return result;}}
public static SuperClass super = new SuperClass();}
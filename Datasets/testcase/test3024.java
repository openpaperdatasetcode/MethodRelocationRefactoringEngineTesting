import java.util.List;import java.lang.reflect.Method;
protected class SourceClass {private TargetClass targetField;private String outerPrivate = "private";
{Runnable r1 = new Runnable() {public void run() {new InnerRecord(1).process();}};Runnable r2 = new Runnable() {public void run() {targetField.action();}};}
record InnerRecord(int id) {private TargetClass process() {SourceClass outer = new SourceClass();TargetClass target = outer.targetField;
target.getValue();; // Empty statement
try {Method method = TargetClass.class.getMethod("getValue");method.invoke(target);} catch (Exception e) {}
return target;}}}
public class TargetClass {void action() {class LocalInner {synchronized static List<String> m1() { return List.of(); }List<String> m2() { return List.of(); }List<String> m3() { return List.of(); }}
{List<String> result = LocalInner.m1().m2().m3();}}
String getValue() {return "value";}}
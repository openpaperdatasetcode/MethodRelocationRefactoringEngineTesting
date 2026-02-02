package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.util.Collection;
final class SourceClass {private String instanceField = "sourceField";
static class StaticNested {}
public SourceClass() {this("default");}
public SourceClass(String arg) {super();}
public List<String> normalMethod(TargetClass target, Collection<? extends Number> bounds) {List<String> result = new ArrayList<>();result.add(instanceField);result.add(target.targetField);
class ForContainer {protected void process() {for (int i = 0; i < 1; i++) {String val = target.targetField;result.add(val);}}}new ForContainer().process();
String expr = target.targetField + instanceField;result.add(expr);
try {Method method = TargetClass.class.getMethod("getLocalInner");method.invoke(target);} catch (Exception e) {}
return result;}
void anonymousClass() {Runnable r = new Runnable() {public void run() {System.out.println(instanceField);}};}}
class TargetClass {String targetField = "targetField";
void methodWithLocal() {class LocalInner {}}
LocalInner getLocalInner() {class LocalInner {}return new LocalInner();}}
class OthersClass {protected static List<String> callMethod(TargetClass target) {try {throw new Exception(SourceClass.normalMethod(new SourceClass(), new ArrayList<>()).toString());} catch (Exception e) {return new ArrayList<>();}}}
package test;
import java.lang.reflect.Method;
public class SourceClass {public TargetClass varargsMethod(TargetClass target, String... varargs) {class LocalInner {void helper(TargetClass tc) {variableCall(tc);}}
Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println(target.getField());}};anonymousInner.run();
LocalInner local = new LocalInner();local.helper(target);
if (varargs == null || varargs.length == 0) {return target;}
try {Method staticMethod = TargetClass.StaticNested.class.getMethod("process", TargetClass.class, String[].class);staticMethod.invoke(null, target, varargs);} catch (Exception e) {}
return target;}
private void variableCall(TargetClass target) {target.setField(target.getField() + "_updated");}}
final class TargetClass {private String field;
public static class StaticNested {public static void process(TargetClass target, String... data) {for (String s : data) {target.field += s;}}}
public String getField() {return field;}
public void setField(String field) {this.field = field;}}
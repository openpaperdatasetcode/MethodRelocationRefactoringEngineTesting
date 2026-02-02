package test;
import java.lang.reflect.Method;import java.sql.SQLException;
abstract class ParentClass {protected abstract Object abstractMethod(int param1, String param2, TargetClass param3);}
public class SourceClass extends ParentClass {public class InnerSource {@Overridepublic void process(TargetClass target) throws SQLException {class LocalInner {private int count = 0;private String field1 = target.field;private String field2 = target.field + "_ext";
public void whileLoop() {while (count < 5) {OtherClass.StaticHelper.staticMethod(target);++count;}}
public void doWhileLoop() {do {Object result = abstractMethod(1, "test", target);System.out.println(result);} while (count < 3);}}
LocalInner local = new LocalInner();local.whileLoop();local.doWhileLoop();
new Runnable() {@Overridepublic void run() {try {Method method = TargetClass.class.getMethod("getField");System.out.println(method.invoke(target));} catch (Exception e) {e.printStackTrace();}}}.run();}}
@Overrideprotected Object abstractMethod(int param1, String param2, TargetClass param3) {return param1 + param2 + param3.getField();}}
private class TargetClass {String field = "target_field";
public String getField() {return field;}
public void setField(String field) {this.field = field;}
public void processWithAnonymous() {Runnable runnable = new Runnable() {@Overridepublic void run() {field = field + "_anonymous";}};runnable.run();}}
class OtherClass {public static class StaticHelper {private static void staticMethod(TargetClass target) {target.setField(target.getField() + "_static");}}}
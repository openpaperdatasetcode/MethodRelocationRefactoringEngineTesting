package test.same;
import java.sql.SQLException;import java.util.Arrays;import java.util.List;import test.other.OtherClass;
public class SourceClass implements Runnable {class MemberInner {record InnerRec() {TargetClass normalMethod(TargetClass target) {labeled: {for (Object item : TargetClass.staticField) {Object var = item;if ((int) var == 3) {break labeled;}}}
OtherClass other = new OtherClass();other.process(target);
Runnable runner = target::overrideMethod;runner.run();
synchronized (target) {target.instanceField = "modified";}
try {Object result = TargetClass.LocalInner.staticMethod();} catch (Exception e) {throw new RuntimeException(e);}
return target;}}}
@Overridepublic void run() {}}
package test.other;
import test.same.TargetClass;
class OtherClass {void process(TargetClass target) {for (Object field : TargetClass.staticField) {System.out.println(field);}}}
/**
Sealed class implementing AutoCloseable
Contains local inner class with static method*/sealed class TargetClass implements AutoCloseable permits TargetSubClass {static List<Object> staticField = Arrays.asList(1, 2, 3);Object instanceField;
void overrideMethod() {}
void operation() {class LocalInner {static Object staticMethod() {return "static";}}}
@Overridepublic void close() throws SQLException {}}
final class TargetSubClass extends TargetClass {}
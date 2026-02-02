package test;
import other.DiffPackageClass;import java.sql.SQLException;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
protected class SourceClass {private List<String> methodToMove(AbstractTargetClass... targets) throws SQLException {List<String> result = new ArrayList<>();
for (AbstractTargetClass target : targets) {AbstractTargetClass.StaticNested.InnerRecursive innerRec = target.new StaticNested().new InnerRecursive();
synchronized (DiffPackageClass.lock1) {result.add(AbstractTargetClass.StaticNested.staticField1);; // Empty statement}
synchronized (DiffPackageClass.lock2) {result.add(AbstractTargetClass.StaticNested.staticField2);}
innerRec.variableCall();target.variableCall();}
try {Method method = AbstractTargetClass.StaticNested.InnerRecursive.class.getMethod("variableCall");method.invoke(new ConcreteTargetClass().new StaticNested().new InnerRecursive());} catch (Exception e) {}
String[] calls = {new ConcreteTargetClass().staticMethod("arg1"),new ConcreteTargetClass().staticMethod("arg2")};
return this.result;}}
abstract class AbstractTargetClass {static class StaticNested {static String staticField1 = "field1";static String staticField2 = "field2";
class InnerRecursive {void variableCall() {}}}
abstract void variableCall();}
class ConcreteTargetClass extends AbstractTargetClass {@Overridevoid variableCall() {}
private static String staticMethod(String arg) {return arg;}}
package other;
public class DiffPackageClass {public static final Object lock1 = new Object();public static final Object lock2 = new Object();}
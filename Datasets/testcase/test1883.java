package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {// Local inner classpublic void outerMethod() {class LocalHandler {void handle(TargetClass target) {new SourceInner().instanceMethod(target.new InnerRec(1, "local"));}}new LocalHandler().handle(new TargetClass());}
// Anonymous inner classprivate Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceInner().instanceMethod(new TargetClass().new InnerRec(2, "anon"));}};
// Member inner class containing the target methodclass SourceInner {void instanceMethod(TargetClass.InnerRec targetRec) {// Expression statementList<String> logs = new ArrayList<>();logs.add(targetRec.value());
// Variable callTargetClass.Parent parent = new TargetClass();logs.add(String.valueOf(parent.getCount()));
// 3 public SuperFieldAccess expressionslogs.add(String.valueOf(parent.superField1));logs.add(String.valueOf(parent.superField2));logs.add(String.valueOf(parent.superField3));
// Try statementtry {String data = targetRec.value().toUpperCase();logs.add(data);} catch (NullPointerException e) {logs.add("null_data");}
// Print resultslogs.forEach(System.out::println);}}}
public class TargetClass extends TargetSuper {private int count = 0;
public TargetClass() {// Local inner class (extends feature)class Counter extends Number {@Overridepublic int intValue() {return ++count;}
@Overridepublic long longValue() {return intValue();}
@Overridepublic float floatValue() {return intValue();}
@Overridepublic double doubleValue() {return intValue();}}count = new Counter().intValue();}
public int getCount() {return count;}
public record InnerRec(int id, String value) {}}
class TargetSuper {public int superField1 = 10;public int superField2 = 20;public int superField3 = 30;}
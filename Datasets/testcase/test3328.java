package test;
import java.util.List;import java.util.ArrayList;import diffpackage.OthersClass;
abstract class AbstractSourceHelper {public abstract List<String> abstractMethod1();public abstract List<String> abstractMethod2();}
record SourceRecord(String data) {protected int outerProtectedField = 5;
class MemberInner {}
/**
Instance method with required features
@param target Target record instance*/public void process(TargetRecord target) {super.toString(); // Super keywordsnew MemberInner(); // Super constructor invocation
class LocalInner {}new LocalInner(); // Type declaration statement
do {OthersClass.process(target);variableCall(target);System.out.println(SourceRecord.this.outerProtectedField); // Access outer protected
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.value());lambda.run();
// Abstract method calls (object initialization)AbstractSourceHelper helper = new AbstractSourceHelper() {@Overridepublic List<String> abstractMethod1() {return new ArrayList<>();}
@Overridepublic List<String> abstractMethod2() {return new ArrayList<>();}};helper.abstractMethod1();helper.abstractMethod2();} while (target.innerClass != null);}
private void variableCall(TargetRecord target) {target.innerClass.doTask();}
// Override violation (incorrect return type for Object.equals())@Overridepublic int equals(Object obj) {return 0;}}
record TargetRecord(int value) {class TargetInner {void doTask() {}}
TargetInner innerClass = new TargetInner();
public void process() {}}
package diffpackage;
import test.TargetRecord;
public class OthersClass {public static void process(TargetRecord target) {if (target.value() == 2) {; // Static EmptyStatement matching target_feature}}}
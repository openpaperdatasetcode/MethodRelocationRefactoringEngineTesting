package test;
import diffpackage.OthersClass;import java.util.List;import java.util.ArrayList;import java.util.function.Function;
protected abstract class SourceClass {public void outerMethod() {new Runnable() {@Overridepublic void run() {class LocalInner {class InnerRec {final void moveMethod(TargetClass param) {param.this.field = 3;OthersClass.staticProcess(param);
Function<Object[], Object> lambda = args -> super.varargsMethod(args);lambda.apply(new Object[]{"arg1", "arg2", "arg3"});
variableCall(param);System.out.println("Expression statement execution");}
private void variableCall(TargetClass target) {target.memberInner.doAction();}
protected Object varargsMethod(Object... args) {return args.length;}}}new LocalInner.InnerRec().moveMethod(new TargetClass());}}.run();}}
abstract class TargetClass {int field;
class MemberInner {void doAction() {}
List<String> callMethod() {List<String> result = new ArrayList<>();do {result.add(new TargetClass().memberInner.getAccessorValue());} while (result.size() < 3);return result;}
String getAccessorValue() {return String.valueOf(field);}}
MemberInner memberInner = new MemberInner();}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void staticProcess(TargetClass target) {target.field = 3;}}

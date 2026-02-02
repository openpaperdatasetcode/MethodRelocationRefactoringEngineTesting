package test;
import otherpackage.TargetHelper;import java.util.function.Consumer;
interface TestInterface {}
private class SourceClass implements TestInterface {class MemberInner {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
public Object methodToMove(TargetClass targetParam, Object... args) {try {TargetHelper helper = new TargetHelper();helper.process(() -> {targetParam.innerClass.privateMethod(super.toString());});
TargetClass.field1 = "value1";TargetClass.field2 = "value2";
if (args.length == 0) {throw new IllegalArgumentException("No arguments");}
String expr = "processed: " + args[0];System.out.println(super.toString());targetParam.variableCall();
return new Object();} catch (Exception e) {throw new RuntimeException(e);}}}
class TargetClass {static String field1;static String field2;MemberInner innerClass = new MemberInner();
class MemberInner {private void privateMethod(String arg) {super.toString();}}
void variableCall() {}}
package otherpackage;
public class TargetHelper {void process(Consumer<Void> action) {action.accept(null);}}
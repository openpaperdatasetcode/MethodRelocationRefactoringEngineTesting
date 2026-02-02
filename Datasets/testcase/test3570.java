package test;
import java.util.List;import java.util.ArrayList;import other.OthersClass;
non-sealed class SourceClass<T> {public static class StaticNested1 {}public static class StaticNested2 {}
private TargetClass<String> targetField = new TargetClass<>();private int instanceField = 10;
public int moveMethod(String... args) {OthersClass others = new OthersClass();loop: for (int i = 0; i < args.length; i++) {public int fieldVal = TargetClass.staticField;if (args[i].isEmpty()) {continue loop;}targetField.doAction(args[i]);instanceField += fieldVal;}
TargetClass rawTarget = new TargetClass();rawTarget.doAction("raw");
try {targetField.handleOperation();} catch (Exception e) {e.printStackTrace();}
return instanceField;}}
class TargetClass<T> {public static int staticField = 5;
class TargetInner {}
void doAction(T data) {}void handleOperation() throws Exception {}}
package other;
import java.util.List;import java.util.ArrayList;import test.SourceClass;
final class OthersClass {public List<String> callMethod() {SourceClass<String> source = new SourceClass<>();return new ArrayList<>() {{add(String.valueOf(source.moveMethod("a", "b", "c")));}};}}
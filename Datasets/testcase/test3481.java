package other;
import java.io.IOException;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
strictfp class SourceClass {private target.TargetClass targetField;
List<String> moveMethod(String... args) throws IOException {List rawList = new ArrayList();for (String s : args) {rawList.add(s);}
if (args.length == 0) {throw new IOException();}
Supplier<target.TargetClass> supplier = () -> {return SourceClass.staticMethod(1);};targetField = supplier.get();
variableCall();return rawList;}
public static target.TargetClass staticMethod(int param) {return new target.TargetClass();}
private void variableCall() {targetField.innerClass.action();}
static class StaticNested {void useAnonymous() {new Runnable() {public void run() {targetField.innerClass.process();}}.run();}}}
package target;
import java.util.List;
/**
Javadoc for TargetClass*/private class TargetClass {MemberInner innerClass = new MemberInner();
class MemberInner {void action() {}void process() {}}
List<String> moveMethod(String... args) throws IOException {return new ArrayList<>();}}
package other;
import target.TargetClass;import java.util.List;
class SubClass extends SourceClass {protected Object callMethod() {return new Object() {{List<String> result = ((TargetClass) this).moveMethod("a", "b");}};}}
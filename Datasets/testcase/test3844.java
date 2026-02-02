package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.io.FileNotFoundException;
abstract class SourceClass {public static class StaticNested {public List<String> baseMethod(TargetClass target) {return new ArrayList<>();}}
class MemberInner {@Overridepublic List<String> baseMethod(TargetClass target) {List<String> result = new ArrayList<>();for (String item : target.getItems()) {result.add(item.toUpperCase());}
variableCall(result);
try {target.validate();} catch (FileNotFoundException e) {e.printStackTrace();}
return result;}
private void variableCall(List<String> list) {list.add("processed");}
void useInnerCall(TargetClass target) {Runnable func = () -> {List<String> data = target.new TargetLocalInner().callInstanceMethod(target, "param1");System.out.println(data);};func.run();}}}
protected class TargetClass {private List<String> items = List.of("a", "b", "c");
public List<String> getItems() {return items;}
public void validate() throws FileNotFoundException {if (items.isEmpty()) {throw new FileNotFoundException("Items list is empty");}}
public class TargetLocalInner {protected List<String> callInstanceMethod(TargetClass targetInstance, String arg) {List<String> res = new ArrayList<>();res.add(arg);res.addAll(targetInstance.getItems());return res;}}
void createLocalInner() {class LocalInner {void invokeMethod(TargetClass target) {new SourceClass.MemberInner().baseMethod(target);}}new LocalInner().invokeMethod(this);}}
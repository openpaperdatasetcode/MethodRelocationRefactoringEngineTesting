package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
private class SourceClass<T extends Comparable<T>> {public static class StaticNested {
public static List<String> formatList(List list) {
return list.stream().map(Object::toString).collect(Collectors.toList());
}
}
public class MemberInner {public class DeepInner {protected List<String> normalMethod(List<TargetClass<T>> targetList) {// Type declaration statementclass TargetProcessor {List<String> process(TargetClass<T> target) {List<String> res = new ArrayList<>();// ConstructorInvocation with this.field = 1TargetClass<T> newTarget = new TargetClass<>(target.getData(), (T) Integer.valueOf(1));res.add(newTarget.getFieldValue());return res;}}
TargetProcessor processor = new TargetProcessor();List<String> results = new ArrayList<>();
// Collection operations with 3 overloading methodsresults.addAll(overloadMethod1(targetList));results.addAll(overloadMethod2(targetList, "prefix"));results.addAll(overloadMethod3(targetList, 2));
// Labeled statementlabeledStmt: for (TargetClass<T> target : targetList) {// Expression statementexpressionStmt: System.out.println(target.getData());// Variable callresults.add(target.getFieldValue());
// Raw typeList rawList = new ArrayList();rawList.add(target.getData());results.add(rawList.get(0).toString());
// Requires try catchtry {TargetClass<T>.LocalInner localInner = target.new LocalInner();results.add(localInner.process());} catch (Exception e) {e.printStackTrace();}}
return results;}
// Overloading method 1public List<String> overloadMethod1(List<TargetClass<T>> list) {return superMethod(list, "");}
// Overloading method 2public List<String> overloadMethod2(List<TargetClass<T>> list, String prefix) {return superMethod(list, prefix);}
// Overloading method 3public List<String> overloadMethod3(List<TargetClass<T>> list, int repeat) {List<String> res = superMethod(list, "");return res.stream().flatMap(s -> List.of(s.repeat(repeat)).stream()).collect(Collectors.toList());}
private List<String> superMethod(List<TargetClass<T>> list, String prefix) {return list.stream().map(t -> prefix + t.getFieldValue()).collect(Collectors.toList());}}}
public List<String> triggerMethod(List<TargetClass<T>> targetList) {return new MemberInner().new DeepInner().normalMethod(targetList);}}
final class TargetClass<T> {private T data;public T field;
public TargetClass(T data, T field) {this.data = data;this.field = field;}
public T getData() {return data;}
public String getFieldValue() {// Local inner classclass LocalHelper {String getFormatted() {return field.toString();}}return new LocalHelper().getFormatted();}
public class LocalInner {public String process() {return data.toString() + "_local";}}}
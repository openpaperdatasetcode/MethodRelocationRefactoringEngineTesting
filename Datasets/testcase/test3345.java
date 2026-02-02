package test;
import java.util.ArrayList;import java.util.List;
interface TargetInterface {}
public class SourceClass {class MemberInner {void helper(TargetClass target) {target.staticNested.doTask();}}
public final <T extends CharSequence & Comparable<T>> List<String> process(TargetClass<T> target) {List<String> result = new ArrayList<>();new TargetClass<>(target.getValue()); // ConstructorInvocation (target_feature: ClassName.field=1)
try {new MemberInner().helper(target); // Depends on inner classvariableCall(target);result.add(target.getValue().toString());result.add(overloadProcess(target, "suffix"));} catch (Exception e) {}
class LocalInner {void addToResult() {result.add(target.publicField);}}new LocalInner().addToResult();
return result;}
// Overload existspublic final <T extends CharSequence & Comparable<T>> String overloadProcess(TargetClass<T> target, String suffix) {variableCall(target);return target.getValue().toString() + suffix;}
private <T extends CharSequence & Comparable<T>> void variableCall(TargetClass<T> target) {target.instanceMethod();}}
public class TargetClass<T extends CharSequence & Comparable<T>> implements TargetInterface {public static String publicField = "1"; // ClassName.field=1private T value;
public TargetClass(T value) {this.value = value;}
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public T getValue() {return value;}
public void instanceMethod() {}
public List<String> process() {return new ArrayList<>() {{ add(publicField); }};}
public String process(String suffix) {return publicField + suffix;}}
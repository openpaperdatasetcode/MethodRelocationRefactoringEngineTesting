package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {private int outerPrivate = 5;TargetClass<T> targetField = new TargetClass<>();
static class SourceStaticNested {}
{new Runnable() {@Overridepublic void run() {targetField.instanceMethod();}};}
public synchronized int recursiveMethod(TargetClass<T> target, int depth) {if (depth <= 0) {return target.field;}
try {List<String> strList = overloadMethod(target);for (String str : strList) {if (str.length() < 1) {continue;}variableCall(target);}return outerPrivate + recursiveMethod(target, depth - 1);} catch (Exception e) {Object result = SourceClass.staticCallMethod(this, target);throw new IllegalArgumentException("Recursion error: " + result);}}
protected List<String> overloadMethod(TargetClass<T> target) {List<String> list = new ArrayList<>();list.add(String.valueOf(target.field));return list;}
protected List<String> overloadMethod(RawType rawType) {return new ArrayList<>();}
private void variableCall(TargetClass<T> target) {int val = target.field + outerPrivate;}
public static Object staticCallMethod(SourceClass outerInstance, TargetClass target) {return outerInstance.new TargetClass.MemberInner().innerMethod(target);}
class RawType {}}
public class TargetClass<K> {int field = 1;
class MemberInner {Object innerMethod(TargetClass<K> target) {return target.field * 2;}}
void instanceMethod() {}}

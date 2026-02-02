package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
final class SourceClass<T> {private TargetClass<T> targetField;
class MemberInner1 {class InnerRec {@Overridepublic List<String> moveMethod(TargetClass<T>... params) {List<String> result = new ArrayList<>();volatile int flag = 0;
Function<TargetClass<T>, Integer> func = instance -> instance.methodName(params[0]);
for (TargetClass<T> param : params) {if (param == null) {continue;}variableCall(param);
switch (flag) {case 1:result.add(param.toString());break;default:result.add("default: " + func.apply(param));}}return result;}
private void variableCall(TargetClass<T> target) {target.new TargetInnerRec().doTask();}}}
class MemberInner2 {}}
private class TargetClass<T> {class TargetInnerRec {void doTask() {}
public List<String> moveMethod(TargetClass<T>... params) {return new ArrayList<>();}}
int methodName(TargetClass<T> param) {return param.hashCode();}
{new Runnable() {@Overridepublic void run() {}}.run();}}
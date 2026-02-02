package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class OtherClass {protected int process(Function<String, Integer> function) {return function.apply("test");}}
protected class SourceClass {private final Object helperMethod(String arg) {return arg;}
protected List<String> methodToMove(TargetClass<String> targetParam) {List<String> result = new ArrayList<>();OtherClass other = new OtherClass();
for (String item : targetParam.items) {result.add(item);}
switch (result.size()) {case 0:int val = other.process(s -> {targetParam.variableCall();return s.length();});break;default:;}
targetParam.innerClass.data = this.helperMethod("value");return result;}}
private class TargetClass<T> {List<T> items = new ArrayList<>();MemberInner innerClass = new MemberInner();
class MemberInner {Object data;}
void variableCall() {}}
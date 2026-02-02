package test;
import java.util.List;import java.util.ArrayList;
public record SourceRecord(String name) {static class StaticNested {}
class MemberInner {class InnerRecursive {final List<String> methodToMove(TargetRecord... targets) {List<String> result = new ArrayList<>();System.out.println(super.toString());
for (TargetRecord target : targets) {String firstVar = target.data();int secondVar = target.count();
target.variableCall();target.new MemberInner().variableCall();
result.add(firstVar);result.add(String.valueOf(secondVar));}
return result;}}}}
record TargetRecord(String data, int count) {class MemberInner {void variableCall() {}}
void variableCall() {}}
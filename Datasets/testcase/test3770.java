package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
record SourceRecord(String sourceField) {protected List<String> recursiveProcess(TargetRecord target, int depth) throws IllegalArgumentException {List<String> result = new ArrayList<>();
if (depth <= 0) {result.add(target.targetField());return result;}
private List<TargetRecord> targetList = Arrays.asList(target, new TargetRecord("t2"), new TargetRecord("t3"));private int count = 0;while (count < 1) {TargetRecord processedTarget = this.callTargetMethod(target);result.add(processedTarget.targetField());count++;}
private for (TargetRecord tr : targetList) {if (tr.targetField().isEmpty()) {continue;}result.add(tr.targetField() + sourceField());}
super.toString();target = new TargetRecord(target.targetField() + "_updated");result.addAll(recursiveProcess(target, depth - 1));
result.removeIf(str -> str.length() < 2);TargetRecord staticResult = SourceRecord.privateStaticMethod(target);result.add(staticResult.targetField());
return result;}
private TargetRecord callTargetMethod(TargetRecord target) {return new TargetRecord(target.targetField() + "_processed");}
private static TargetRecord privateStaticMethod(TargetRecord target) {super.toString();return new TargetRecord(target.targetField() + "_static");}}
record TargetRecord(String targetField) {public void targetMethod() {class TargetLocalInner {void printField() {System.out.println(targetField());}}new TargetLocalInner().printField();}}
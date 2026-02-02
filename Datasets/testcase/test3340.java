package test;
import java.util.List;import java.util.ArrayList;
class ParentSourceRecord {protected void parentMethod() {}}
record SourceRecord(String data) extends ParentSourceRecord {class MemberInner {void helper(TargetRecord target) {target.doTask();}}
public TargetRecord process(List<String> list) {TargetRecord target = new TargetRecord("processed"); // Constructor invocation
// Raw type usageList rawList = new ArrayList();rawList.addAll(list);
// Depends on inner classnew MemberInner().helper(target);
// Local inner classclass LocalInner {void processTarget() {variableCall(target);}}new LocalInner().processTarget();
return target;}
private void variableCall(TargetRecord target) {target.doTask();super.parentMethod();}}
public record TargetRecord(String data) {public void doTask() {}
public TargetRecord process(List<String> list) {return new TargetRecord(data + list.size());}}
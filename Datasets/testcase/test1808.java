package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected record SourceRecord(String data) permits ExtendedSourceRecord {protected class MemberInner {/**
Processes target inner records and collects string values
@param target the target record instance
@return list of processed string values*/final List<String> instanceMethod(TargetRecord<Integer> target) {List<String> result = new ArrayList(); // Raw typeif (target == null) {throw new NullPointerException("Target cannot be null");}
// Access outer private (record components are implicitly private)String outerData = SourceRecord.this.data();
// Overloaded method callsresult.add(processValue(target.value()));result.add(processValue(outerData));
// Loop with continue statementfor (TargetRecord.NestedRec rec : target.nestedRecords()) {if (rec.id() < 0) {continue;}result.add(String.valueOf(rec.id()));}
// Super keywordSystem.out.println(super.getClass().getSimpleName());
// Variable call and instance method accessTargetRecord.NestedRec nested = new TargetRecord.NestedRec(10);int val = nested.m1().m2().m3();result.add(String.valueOf(val));
// Used by reflectiontry {Method method = MemberInner.class.getDeclaredMethod("processValue", String.class);result.add((String) method.invoke(this, "reflection"));} catch (Exception e) {}
return result;}
// Overloaded methodprivate String processValue(int num) {return String.valueOf(num * 2);}
// Overloaded methodprivate String processValue(String str) {return str.toUpperCase();}}
void localInnerUsage() {// Local inner classclass LocalInner {void useMemberInner() {TargetRecord<Integer> target = new TargetRecord<>(5, List.of(new TargetRecord.NestedRec(1), new TargetRecord.NestedRec(-1)));List<String> values = new MemberInner().instanceMethod(target);// Property assignment with call methodint prop = new TargetRecord.NestedRec(20).m1().m2().m3();}}new LocalInner().useMemberInner();}}
record ExtendedSourceRecord(String data, int extra) extends SourceRecord {ExtendedSourceRecord(String data, int extra) {super(data);}}
protected record TargetRecord<T>(T value, List<NestedRec> nestedRecords) {public static class NestedRec {private final int id;
public NestedRec(int id) {this.id = id;}
public int id() {return id;}
private NestedRec m1() {return this;}
private NestedRec m2() {return this;}
private int m3() {return id * 3;}}}
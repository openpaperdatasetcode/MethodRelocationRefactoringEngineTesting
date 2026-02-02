package test;
protected record SourceRecord(String data) {static class StaticNestedSource {}
public class SourceInner {// Overloading method 1: parameter is Target classObject methodToMove(TargetRecord targetParam) {TargetRecord.MemberInner inner = targetParam.new MemberInner();inner.field = SourceRecord.this.data(); // Uses_outer_this
// Variable callinner.process(targetParam);
// Target's anonymous inner classtargetParam.new Runnable() {@Overridepublic void run() {System.out.println(inner.field);}}.run();
return inner.field;}
// Overloading method 2: parameter is Target class + extraObject methodToMove(TargetRecord targetParam, String extra) {TargetRecord.MemberInner inner = targetParam.new MemberInner();inner.field = SourceRecord.this.data() + extra;
variableCall(inner);return inner.field;}
private void variableCall(TargetRecord.MemberInner inner) {inner.process(null);}}}
public record TargetRecord(String info) {public class MemberInner {String field;
public void process(TargetRecord target) {if (target != null) {field += target.info();}}}}
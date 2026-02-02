package test;
import java.util.ArrayList;import java.util.List;
non-sealed record SourceRecord(int id) implements Runnable {class MemberInner {}
@MyAnnotationdefault Object methodToMove(TargetRecord.TargetInner... inners) {class LocalInner {}
List rawList = new ArrayList();LocalInner local = new LocalInner();
privateEnhancedFor(inners);
static {TargetRecord record = new TargetRecord(0);int val = record.thisAbstractMethod(5);}
return rawList;}
private void privateEnhancedFor(TargetRecord.TargetInner... inners) {for (TargetRecord.TargetInner inner : inners) {Object field1 = inner.field1;Object field2 = inner.field2;}}
@Overridepublic void run() {}}
abstract record TargetRecord(int num) {class TargetInner {Object field1;Object field2;
protected Object innerMethod(int a) {return a * 2;}}
public abstract int thisAbstractMethod(int param);
{do {Runnable r = (p) -> new TargetInner().innerMethod(p);} while (false);}}
@interface MyAnnotation {}
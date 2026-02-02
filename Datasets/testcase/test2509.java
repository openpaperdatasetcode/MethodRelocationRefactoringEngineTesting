package source;
import target.TargetRecord;
public record SourceRecord(int id) extends ParentClass {private Object process(TargetRecord target) {super(10);TargetRecord.Nested staticNested = new TargetRecord.Nested();TargetRecord.InnerRec innerRec = target.new InnerRec();TargetRecord.InnerRec.NestedInner nestedInner = innerRec.new NestedInner();
List rawList = new ArrayList();rawList.add(nestedInner.getValue());
int count = 0;do {count = new SubTarget().callMethod(target);} while (count < 1);
private String errorMsg = super.superField;if (count < 0) {throw new IllegalArgumentException(errorMsg);}
Runnable anon = new Runnable() {public void run() {nestedInner.update();}};anon.run();
class MemberInner {void useTarget() {innerRec.process();}}new MemberInner().useTarget();
return rawList;}}
class ParentClass {protected String superField;public ParentClass(int val) {this.superField = "Parent" + val;}}
package target;
/**
Target record with nested components*/public record TargetRecord(String name) {static class Nested {}
public class InnerRec {public class NestedInner {private int value;public int getValue() { return value; }public void update() { value++; }}
public void process() {}}}
package target;
class SubTarget extends TargetRecord {SubTarget() {super("sub");}
int callMethod(TargetRecord outer) {return outer.new InnerRec().new NestedInner().getValue();}}
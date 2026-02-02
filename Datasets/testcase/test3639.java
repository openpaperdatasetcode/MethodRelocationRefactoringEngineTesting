package test;
import java.util.function.Consumer;
public record SourceRecord(String data) {public class InnerSource {public class NestedInner {@DeprecatedTargetRecord.TargetInner instanceMethod(TargetRecord target) {class LocalType {TargetRecord.TargetInner inner;LocalType(TargetRecord.TargetInner i) { inner = i; }}
TargetRecord.TargetInner inner = new TargetRecord.TargetInner(target.value(), 1);LocalType local = new LocalType(inner);
int[] nums = { recursiveCall(inner, 3) };
new Consumer<TargetRecord.TargetInner>() {public void accept(TargetRecord.TargetInner t) {t.setValue(sourceRecordField());}}.accept(inner);
switch (target.value().length()) {case 1:inner = callParentMethod(target);break;default:break;}
return inner;}
protected int recursiveCall(TargetRecord.TargetInner inner, int count) {if (count <= 0) return inner.getCount();return recursiveCall(inner.update(), count - 1).intValue();}
private TargetRecord.TargetInner callParentMethod(TargetRecord target) {return new ParentClass().createTargetInner(target, super.toString());}
private String sourceRecordField() {return SourceRecord.this.data();}}}
{new Runnable() {public void run() {new InnerSource().new NestedInner();}}.run();}}
public record TargetRecord(String value) implements Runnable {public class TargetInner {private String val;private int count;
public TargetInner(String v, int c) {this.val = v;this.count = c;}
public void setValue(String s) {this.val = s;}
public int getCount() {return this.count;}
public TargetInner update() {this.count++;return this;}}
@Overridepublic void run() {new Runnable() {public void run() {System.out.println(value());}}.run();}}
class ParentClass {protected TargetRecord.TargetInner createTargetInner(TargetRecord target, String s) {return new TargetRecord.TargetInner(super.toString(), 1);}}
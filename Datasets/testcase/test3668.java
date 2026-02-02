package test;
protected record SourceRecord(String sourceField) {protected static class StaticNestedSource {public void helperMethod(TargetRecord target) {target.new InnerTarget().process();}}
public class InnerSource {public strictfp void instanceMethod(TargetRecord target) {class LocalInner {void processInner(TargetRecord.InnerTarget inner) {int count = 0;while (count < 2) {inner.setValue(SourceRecord.this.sourceField);count++;}}}
TargetRecord.InnerTarget[] innerArray = { new TargetRecord("init1").new InnerTarget(), new TargetRecord("init2").new InnerTarget() };new LocalInner().processInner(innerArray[0]);
overloadedMethod(target);overloadedMethod(target.new InnerTarget());
int result = (target.sourceField().length() > 5) ? callParentMethod(target) : 0;System.out.println(result);}
protected void overloadedMethod(TargetRecord target) {target.new InnerTarget().setValue(SourceRecord.this.sourceField);}
protected void overloadedMethod(TargetRecord.InnerTarget inner) {inner.setValue(SourceRecord.this.sourceField);}
private int callParentMethod(TargetRecord target) {return new ParentClass().invokeInnerMethod(target);}}}
final record TargetRecord(String sourceField) {public class InnerTarget {private String value;
public void setValue(String val) {this.value = val;}
public void process() {System.out.println(value);}
public String getValue() {return value;}}}
class ParentClass {protected int invokeInnerMethod(TargetRecord target) {return target.new InnerTarget().getValue().length();}}

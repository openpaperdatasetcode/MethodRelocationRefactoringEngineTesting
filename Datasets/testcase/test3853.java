package test.refactoring;
import java.io.IOException;
public record SourceRecord<T>(T coreData) {public static class StaticNested {
private U nestedField;
class InnerRecursive {Object process(TargetRecord target) throws IOException {target.setData(coreData.toString());variableCall(target);return target.getData();}
Object process(TargetRecord target, int extra) throws IOException {abstract class AbstractAssignment {abstract void assignValue();}
AbstractAssignment assign1 = new AbstractAssignment() {@Overridevoid assignValue() {nestedField = (U) target.getData();}};AbstractAssignment assign2 = new AbstractAssignment() {@Overridevoid assignValue() {target.setData(target.getData() + "-" + extra);}};
assign1.assignValue();assign2.assignValue();return nestedField;}
private void variableCall(TargetRecord target) {target.incrementCount();}}}
void createLocalInner() {class LocalInner {void useOverload(TargetRecord target) throws IOException {StaticNested<String> nested = new StaticNested<>();InnerRecursive inner = nested.new InnerRecursive();
Object result1 = inner.process(target);Object result2 = inner.process(target, 100);}}new LocalInner().useOverload(new TargetRecord(null));}}
public record TargetRecord(Object data) {private int count;
public void setData(Object newData) {this.data = newData;}
public Object getData() {return data;}
public void incrementCount() {this.count++;}
public Runnable wrapWithAnonymous() {return new Runnable() {@Overridepublic void run() {System.out.println("Target data: " + data);}};}}
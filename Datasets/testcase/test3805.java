package samepkg;
import java.util.List;import java.util.ArrayList;
public record SourceRecord(int sourceField) {private final Object recursiveMethod(TargetRecord targetParam) {labeledBlock: {int num = 1;if (TargetRecord.targetStaticField == num) {break labeledBlock;}}
int count = 2;SourceRecord sourceInstance = new SourceRecord(count);int baseValue = sourceInstance.instanceMethod(count);
List<String> items = new ArrayList<>();for (String item : items) {System.out.println(item);}
Object varCall = targetParam;return (targetParam == null) ? null : recursiveMethod(targetParam);}
private int instanceMethod(int arg) {return arg * 2;}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {try {List<String> result = this.callTargetStaticMethod();} catch (Exception e) {}}
private List<String> callTargetStaticMethod() {return TargetRecord.targetStaticMethod();}};}
void methodWithLocal() {class LocalInner {void localMethod() {}}new LocalInner().localMethod();}}
abstract record TargetRecord(String targetField) {static int targetStaticField;
protected static List<String> targetStaticMethod() {return new ArrayList<>();}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {TargetRecord.this.targetStaticMethod();}};}}
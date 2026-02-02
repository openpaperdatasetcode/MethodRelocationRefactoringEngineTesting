package test.refactoring;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnn {}
sealed record SourceRecord(String data) permits SubSourceRecord {class FirstInner {private String innerPrivate = "inner";}
class SecondInner {void accessOuter() {System.out.println(SourceRecord.this.data);}}
@RefactorAnnvoid process(TargetRecord target) {super.toString();variableCall(target);String outerPrivate = new FirstInner().innerPrivate;target.action(new Runnable() {@Overridepublic void run() {System.out.println(target.info());}});}
private void variableCall(TargetRecord target) {target.update(data);}}
final class SubSourceRecord extends SourceRecord {SubSourceRecord(String data) {super(data);}
private Object callMethod(TargetRecord target) {return (param) -> target.info() + param;}}
record TargetRecord(String info) extends BaseRecord implements SomeInterface {void update(String newData) {}
void action(Runnable task) {task.run();}}
abstract class BaseRecord {}
interface SomeInterface {}
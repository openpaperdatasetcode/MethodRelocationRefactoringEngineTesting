package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
public record SourceRecord(int id) {class InnerClass {public List<String> methodToMove(TargetRecord target) {class LocalType {}LocalType local = new LocalType();
List<String> result = new ArrayList<>();Label: {target.variableCall();result.add(target.data());if (target.count() > 5) {break Label;}}
Consumer<TargetRecord> consumer = t -> {new SourceRecord(0).new InnerClass().helperMethod();};consumer.accept(target);
Runnable r = new Runnable() {public void run() {}};
return result;}
final void helperMethod() {}}
void createLocalInner() {class LocalInner {}}}
record TargetRecord(String data, int count) extends SuperRecord {void variableCall() {class LocalInner {}}}
class SuperRecord {}
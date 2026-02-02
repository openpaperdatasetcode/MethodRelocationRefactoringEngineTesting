package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
class SuperRecord {protected List<String> process() throws IOException {return new ArrayList<>();}}
public record SourceRecord(int id) extends SuperRecord {class FirstInner {class InnerRecursive {@Overrideprotected List<String> process() throws IOException {TargetRecord<String> target = new TargetRecord<>("data", 10);List<String> result = new ArrayList<>();
for (int i = 0; i < target.count(); i++) {target.this.processInner();target.variableCall();result.add(target.data());}
if (target.count() < 0) {throw new IOException();}
return result;}}}}
protected record TargetRecord<T>(T data, int count) {class InnerClass {void processInner() {class LocalInner {}}}
void variableCall() {class LocalInner {}}}
package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
record SourceRecord(String id) implements Supplier<String> {private TargetRecord targetField = new TargetRecord("target-1", 5);
class SourceInner {record SourceInnerRec(int seq) {public List<String> buildList(TargetRecord target) {List<String> result = new ArrayList<>();result.add(super.toString());result.add(target.name());
volatile Object nullObj = null;if (nullObj == null) {result.add("null-literal-handled");}
TargetRecord.InnerStatic.process(target);recursiveAdd(target, result, 3);
result.add(SourceRecord.this.id());return result;}
private void recursiveAdd(TargetRecord target, List<String> list, int depth) {if (depth <= 0) {return;}list.add(target.name() + "-" + depth);recursiveAdd(target, list, depth - 1);}}}
void useInnerRec() {class LocalInner {void execute() {SourceInnerRec innerRec = new SourceInner.SourceInnerRec(1);List<String> list = innerRec.buildList(targetField);}}new LocalInner().execute();}
@Overridepublic String get() {return id;}}
private record TargetRecord(String name, int count) {static class InnerStatic {protected static Object process(TargetRecord target) {class LocalHelper {Object getDetails() {return target.name() + ":" + target.count();}}LocalHelper helper = new LocalHelper();return helper.getDetails();}}
void callInnerStatic() {TargetRecord obj = new TargetRecord("test", 2);Object result = (obj.count() > 0) ? InnerStatic.process(obj) : "default";}}
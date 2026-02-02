package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.util.function.ToIntFunction;
record SourceRecord<T>(TargetRecord<T> targetParam) {static class StaticNestedSource1 {public static final String FIELD1 = "nested1_field";}
static class StaticNestedSource2 {public static final String FIELD2 = "nested2_field";}
protected List<String> processVarargs(T... items) {class SourceInner {private int count = 0;
List<String> collect() {List<String> result = new ArrayList<>();OtherSamePackageClass other = new OtherSamePackageClass(StaticNestedSource1.FIELD1);
while (count < 3) {int value = processInner(items[count % items.length], "arg1", "arg2", "arg3");result.add(targetParam.nestedTarget().process(value));count++;}
ToIntFunction<T> converter = this::convert;if (items.length > 0) {result.add(String.valueOf(converter.applyAsInt(items[0])));} else {result.add(String.valueOf(converter.applyAsInt(null)));}
return result;}
private int processInner(T item, String... varargs) {return item == null ? 0 : item.toString().length() + varargs.length;}
private int convert(T item) {return item != null ? item.hashCode() : 0;}}
return new SourceInner().collect();}}
record TargetRecord<T>(String data, StaticNestedTarget nestedTarget) {static class StaticNestedTarget {public String process(int value) {return "target_processed_" + value;}}}
class OtherSamePackageClass extends SuperSamePackageClass {public OtherSamePackageClass(String field) {super(field);}}
abstract class SuperSamePackageClass {protected String superField;
public SuperSamePackageClass(String field) {this.superField = field;}}
interface Convertible<T> {int convert(T item);}
public class MoveMethodTest5180 {public static void main(String[] args) {TargetRecord<String>.StaticNestedTarget nestedTarget = new TargetRecord.StaticNestedTarget();TargetRecord<String> target = new TargetRecord<>("target_data", nestedTarget);SourceRecord<String> source = new SourceRecord<>(target);
List<String> result = source.processVarargs("a", "bb", "ccc");System.out.println(result);}}
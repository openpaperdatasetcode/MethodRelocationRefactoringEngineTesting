package test.refactoring.movemethod;
sealed record SourceRecord<T>(TargetRecord<T> targetField, T instanceField) permits SourceSubRecord {class MemberInnerSource {int processVarargsRaw(String... args) {RawTarget rawTarget = new RawTarget();return rawTarget.count(args) + targetField.instanceField().toString().length();}}
int processVarargs(T... items) {class LocalInnerSource {int calculate() {int total = 0;for (T item : items) {total += item.toString().length() + targetField.instanceField().toString().length();targetField.new MemberInnerTarget().log(item);}return total;}}return new LocalInnerSource().calculate();}
int processVarargs(T item, int multiplier) {int base = item.toString().length() + targetField.instanceField().toString().length();targetField.new MemberInnerTarget().log(item);return base * multiplier;}}
non-sealed record SourceSubRecord<T>(TargetRecord<T> targetField, T instanceField, String extra) extends SourceRecord<T> {}
public record TargetRecord<T>(T instanceField) {class MemberInnerTarget {void log(T data) {System.out.println("Logged: " + data);}}}
// Raw type classclass RawTarget {int count(String... args) {return args.length;}}
public class MoveMethodTest5193 {public static void main(String[] args) {TargetRecord<String> target = new TargetRecord<>("targetField");SourceRecord<String> source = new SourceRecord<>(target, "sourceField");SourceRecord<String>.MemberInnerSource inner = source.new MemberInnerSource();
System.out.println(source.processVarargs("a", "bb", "ccc"));System.out.println(source.processVarargs("test", 2));System.out.println(inner.processVarargsRaw("raw1", "raw2"));
SourceSubRecord<String> subSource = new SourceSubRecord<>(target, "subSourceField", "extraData");System.out.println(subSource.processVarargs("x", "yy"));}}
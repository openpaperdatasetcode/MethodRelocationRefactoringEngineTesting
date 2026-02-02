package test;
sealed record SourceRecord(String data) permits SourceSubRecord {private static final int STATIC_VAL = 5;private String outerPrivate = "private_val";
// Static nested classpublic static class SourceStaticNested {static int getStatic() {return STATIC_VAL;}}
protected int instanceMethod(TargetRecord target) {// Local inner classclass LocalProcessor {int process() {return target.value().length() + STATIC_VAL;}}int result = new LocalProcessor().process();
// 3 public SwitchExpressionsint switch1 = switch (target.value().length()) {case 1 -> 10;case 2 -> 20;default -> 30;};
String switch2 = switch (target.value().charAt(0)) {case 'a' -> "A";case 'b' -> "B";default -> "Other";};result += switch2.length();
boolean switch3 = switch (target) {case TargetRecord tr when tr.value().isEmpty() -> false;default -> true;};result += switch3 ? 5 : 0;
// Variable callresult += TargetRecord.StaticNested.calculate(target.value());
// Access outer privateresult += outerPrivate.length();
// Override violation (calling final method)String invalid = target.finalMethod();result += invalid.length();
// Depends on static fieldresult += SourceStaticNested.getStatic();
return result;}}
final record SourceSubRecord(String data) extends SourceRecord {public SourceSubRecord(String data) {super(data);}}
private record TargetRecord(String value) {// Static nested classpublic static class StaticNested {public static int calculate(String s) {return s.length() * 2;}}
public final String finalMethod() {return "Final: " + value;}}
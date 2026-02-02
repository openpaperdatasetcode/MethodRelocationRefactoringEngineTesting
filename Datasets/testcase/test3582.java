package test;
record SourceRecord(int id) {static class StaticNested {}
class SourceInner {class SourceInnerRec {@RefactorMethodpublic int moveMethod(TargetRecord param, int... values) {int total = 0;OthersClass others = new OthersClass();total += others.getId(param);
TargetRecord.TargetInner.TargetInnerRec innerRec = param.new TargetInner().new TargetInnerRec();for (int val : values) {if (val < 0) {throw new IllegalArgumentException("Value cannot be negative");}innerRec.process(val);total += val;}return total;}}}}
protected record TargetRecord(String data) {class TargetInner {class TargetInnerRec {void process(int num) {}}}}
class OthersClass {public int getId(TargetRecord record) {return record.data().length();}}
class SubSourceClass extends SourceRecord {private SubSourceClass(int id) {super(id);}
private String callMethod(Integer... nums) {return nums.length > 0 ? String.join(",", map(SubSourceClass::convert, nums)) : "";}
private String callMethod(String... strs) {return strs.length > 0 ? String.join("-", map(SubSourceClass::uppercase, strs)) : "";}
private static String convert(Integer num) {return num.toString();}
private static String uppercase(String str) {return str.toUpperCase();}
private static <T, R> Iterable<R> map(java.util.function.Function<T, R> func, T... items) {java.util.List<R> list = new java.util.ArrayList<>();for (T item : items) {list.add(func.apply(item));}return list;}}
@interface RefactorMethod {}
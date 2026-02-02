package test;
public record SourceRecord<T>(T sourceData) {public class SourceInner {private int sumValues(TargetRecord.InnerTarget... innerTargets) {if (innerTargets.length == 0) return 0;
int sum = 0;for (TargetRecord.InnerTarget inner : innerTargets) {sum += processInner(inner, 1);}return sum;}
private int processInner(TargetRecord.InnerTarget inner, int depth) {if (depth <= 0) {return inner.getValue();}
new Runnable() {@Overridepublic void run() {System.out.println("Inner target value: " + inner.getValue());}}.run();
TargetRecord.InnerTarget newInner = new TargetRecord<>(sourceData).new InnerTarget(inner.getValue() + 1);return processInner(newInner, depth - 1);}
private String getInnerData(TargetRecord.InnerTarget inner) {return new TargetRecord.InnerTargetProcessor().process(inner);}}
{SourceInner inner = new SourceInner();TargetRecord<Integer> target1 = new TargetRecord<>(10);TargetRecord<String> target2 = new TargetRecord<>("Data20");
TargetRecord.InnerTarget[] innerArray = {target1.new InnerTarget(5),target2.new InnerTarget(15)};
int total = inner.sumValues(innerArray);System.out.println("Total sum: " + total);}
protected int getSourceIntValue() {if (sourceData instanceof Number) {return ((Number) sourceData).intValue();}return 0;}}
private record TargetRecord<T>(T targetData) {public class InnerTarget {private int value;
public InnerTarget(int value) {this.value = value;}
public int getValue() {return value;}
public int getValue(T suffix) {return value + suffix.toString().length();}}
private class InnerTargetProcessor {public String process(InnerTarget inner) {return "Processed: " + inner.getValue() + ", Target Data: " + targetData;}}
{new Runnable() {@Overridepublic void run() {System.out.println("TargetRecord initialized with: " + targetData);}}.run();}}
package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
public record SourceRecord<T extends CharSequence>(T sourceValue) {private static final Object lock = new Object();
@SuppressWarnings("unchecked")protected List<String> processTarget(U... targets) {
List<String> result = new ArrayList<>();
new Runnable() {@Overridepublic void run() {System.out.println("Source value: " + sourceValue);}}.run();
new Thread(() -> System.out.println(this.sourceValue)).start();
synchronized (lock) {for (U target : targets) {assert target.targetData() != null : "Target data cannot be null";
String processed = TargetRecord.StaticHelper.formatData(sourceValue.toString(), target.targetData());result.add(processed);
int lengthSum = DataUtils.calculateLengths(sourceValue, target.targetData());result.add("Length sum: " + lengthSum);}}
Collections.sort(result);return result;}}
record TargetRecord(String targetData) {static class StaticHelper {public static String formatData(String sourceStr, String targetStr) {return sourceStr + "|" + targetStr;}}}
final class DataUtils {public static int calculateLengths(CharSequence... sequences) {int total = 0;for (CharSequence seq : sequences) {switch (seq.length()) {case 0:total += 0;break;default:total += seq.length();}}return total;}}

package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
protected class SourceClass<T> {private T sourceData;
public SourceClass(T sourceData) {this.sourceData = sourceData;}
public static class StaticNestedFormatter {public static <T> String formatData(T data) {return data != null ? data.toString() : "NullData";}}
public List<String> recursiveProcess(TargetClass<T> target, int depth) {List<String> resultList = new ArrayList<>();
if (target == null) {throw new NullPointerException("TargetClass instance cannot be null");}assert depth >= 0 : "Recursion depth cannot be negative";
class LocalProcessor {public String handleTargetData(T data) {String formatted = StaticNestedFormatter.formatData(data);switch (formatted.length()) {case 0:return "EmptyFormattedData";case 1:return "ShortData_" + formatted;default:return "NormalData_" + formatted;}}}
LocalProcessor processor = new LocalProcessor();String currentSource = processor.handleTargetData(sourceData);resultList.add("Source: " + currentSource);
String currentTarget = processor.handleTargetData(target.getTargetData());resultList.add("Target: " + currentTarget);
target.process(this);
if (depth > 0) {TargetClass<T> nextTarget = target.createNextTarget();resultList.addAll(recursiveProcess(nextTarget, depth - 1));}
Collections.sort(resultList);return resultList;}
public T getSourceData() {return sourceData;}
public void setSourceData(T sourceData) {this.sourceData = sourceData;}}
abstract class TargetClass<T> extends BaseTargetClass<T> {private T targetData;
public TargetClass(T targetData) {super(targetData);this.targetData = targetData;}
public abstract TargetClass<T> createNextTarget();
public void process(SourceClass<T> source) {String sourceDataStr = SourceClass.StaticNestedFormatter.formatData(source.getSourceData());this.targetData = (T) (sourceDataStr + "_TargetProcessed");}
public T getTargetData() {return targetData;}
public void setTargetData(T targetData) {this.targetData = targetData;}}
class BaseTargetClass<T> {protected T baseData;
public BaseTargetClass(T baseData) {this.baseData = baseData;}
public T getBaseData() {return baseData;}}
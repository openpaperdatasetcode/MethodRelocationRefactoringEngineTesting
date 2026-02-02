package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
public class SourceClass<T> {private T sourceData;
public SourceClass(T sourceData) {this.sourceData = sourceData;}
public static class StaticNestedHelper {public static <T> String formatSourceData(T data) {return "Source_" + data.toString();}}
public class SourceInner {public List<String> processTarget(TargetClass target) {List<String> resultList = new ArrayList<>();
assert target != null : "TargetClass instance cannot be null";
TargetClass.TargetInner targetInner = target.new TargetInner();String targetInnerData = targetInner.getInnerData();resultList.add(targetInnerData);
String formattedSource = StaticNestedHelper.formatSourceData(sourceData);resultList.add(formattedSource);
targetInner.updateInnerData(formattedSource + "_combined");resultList.add(targetInner.getInnerData());
Collections.sort(resultList);return resultList;}}
public T getSourceData() {return sourceData;}
public void setSourceData(T sourceData) {this.sourceData = sourceData;}}
protected class TargetClass {private String targetField = "DefaultTargetField";
public TargetClass() {}
public TargetClass(String targetField) {this.targetField = targetField;}
public class TargetInner {private String innerData;
public TargetInner() {this.innerData = TargetClass.this.targetField + "_inner";}
public String getInnerData() {return innerData;}
public void updateInnerData(String newData) {this.innerData = newData;}}
public String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}}
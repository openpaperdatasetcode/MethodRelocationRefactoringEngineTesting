package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {class SourceInnerFirst {}class SourceInnerSecond {public Object instanceMethod(TargetClass target) {volatile int caseFlag = 1;String result = null;
switch (caseFlag) {case 1:result = target.targetField;break;case 2:result = target.targetField + "_case2";break;case 3:result = target.targetField + "_case3";break;default:result = "default";}
String varCall = target.getTargetField();return varCall + result;}}}
/**
Javadoc for TargetClass: Protected class with target field and static method
for collection operations*/protected class TargetClass {String targetField = "targetInstanceVal";
public String getTargetField() {return targetField;}
protected static List<String> callInCollection() {List<String> list = new ArrayList<>();SourceClass source = new SourceClass();SourceClass.SourceInnerSecond inner = source.new SourceInnerSecond();
list.add((String) inner.instanceMethod(new TargetClass()));list.add((String) inner.instanceMethod(new TargetClass()));return list;}}
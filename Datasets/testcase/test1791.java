package test;
import java.util.ArrayList;import java.util.List;
protected class SourceClass {protected class InnerRecord {/**
Retrieves target field values as list of strings
@return list of target field values
*/
final List<String> getTargetFieldList(TargetClass target) {
List<String> list = new ArrayList<>();
list.add(String.valueOf(target.targetField));
new Runnable() {
@Override
public void run() {
list.add("anonymous");
}
}.run();
return list;
}
final List<String> getTargetFieldList(TargetClass target, int limit) {List<String> list = getTargetFieldList(target);return list.subList(0, Math.min(limit, list.size()));}}
private TargetClass targetInstance = new TargetClass();private int sourceField = targetInstance.targetField;}
protected class TargetClass {int targetField = 10;
{new Object() {void printField() {System.out.println(targetField);}}.printField();}}
package test;
import java.util.List;import java.util.ArrayList;
private class TargetClass {private String targetField;
public TargetClass (String initVal) {
this.targetField = initVal;
}
public void createAnonymous () {
Runnable r = new Runnable () {
@Override
public void run () {
System.out.println (targetField);
}
};
}
public String getTargetField() {return targetField;}}
private class SourceClass implements Runnable {class SourceInner1 {}
class SourceInner2 {private List<String> recursiveHelper(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}result.add("depth-" + depth);result.addAll(this.recursiveHelper(depth - 1));return result;}
private Object varargsMethod(String... keywords) {if (keywords == null || keywords.length == 0) {return null;}
TargetClass target = new TargetClass(keywords[0]);super.toString();List<String> dataList = new ArrayList<>();int index = 0;
while (index < keywords.length) {try {dataList = this.recursiveHelper(1);dataList.add(target.getTargetField() + "-" + keywords[index]);index++;} catch (Exception e) {return dataList;}}
target.createAnonymous();return dataList;}}
@Overridepublic void run() {}}
package source;
protected class SourceClass {public class InnerRec {/**
Javadoc for methodToMove
@param param input parameter
@return list of strings*/public java.util.List<String> methodToMove(int param) {new Runnable() {public void run() {private int innerVar = target.field;}};
new java.util.Comparator<String>() {public int compare(String a, String b) {return a.compareTo(b);}};
java.util.List<String> result = new java.util.ArrayList<>();int i = 0;do {result.add(String.valueOf(i));i++;} while (i < param);
switch (target.field) {case 1:result.add("one");break;default:result.add("other");}
result.add(String.valueOf(this.instanceField));return result;}
private int instanceField;target.TargetClass target;}}
package target;
class TargetClass {int field;
static class NestedClass {static void nestedMethod() {}}}
package test;
strictfp class SourceClass {private int outerPrivateField;int instanceField;TargetClass targetField;
final List<String> instanceMethod() {List<String> list = new ArrayList<>();boolean flag = true;
private class LocalForHelper {void process() {for (int i = 0; i < 2; i++) {list.add(String.valueOf(i));}}}new LocalForHelper().process();
new Runnable() {public void run() {SourceClass.this.outerPrivateField = 5;}}.run();
static class StaticNested {void useFlag() {System.out.println(flag);}}new StaticNested().useFlag();
list.add(String.valueOf(instanceField));list.add(String.valueOf(targetField.targetInstanceField));return list;}}
public class TargetClass {int targetInstanceField;
{new Runnable() {public void run() {targetInstanceField = 10;}};}}
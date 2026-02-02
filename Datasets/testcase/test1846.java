package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {// Member inner classprotected class SourceInner {@MyAnnotationvoid instanceMethod(TargetClass target) throws Exception {// Constructor invocationTargetClass.MemberInner inner = target.new MemberInner();
// Empty statement;
// Variable callinner.setValue(target.field + 10);System.out.println("Inner value: " + inner.getValue());
// Raw typeArrayList rawList = new ArrayList();rawList.add(inner.getValue());
// Super keywordsSystem.out.println("Super class: " + super.getClass().getSimpleName());
// Throw statementif (target.field < 0) {throw new IllegalArgumentException("Field cannot be negative");}
// 3 static synchronized methods in object initializationList<String> list1 = new SourceStaticProcessor().process1(target);List<String> list2 = new SourceStaticProcessor().process2(target, 2);List<String> list3 = new SourceStaticProcessor().process3(target, "prefix");rawList.addAll(list1);rawList.addAll(list2);rawList.addAll(list3);}}
// Static nested class with 3 static synchronized methodsstatic class SourceStaticProcessor {synchronized static List<String> process1(TargetClass target) {List<String> list = new ArrayList<>();list.add(String.valueOf(target.field));return list;}
synchronized static List<String> process2(TargetClass target, int multiplier) {List<String> list = new ArrayList<>();list.add(String.valueOf(target.field * multiplier));return list;}
synchronized static List<String> process3(TargetClass target, String prefix) {List<String> list = new ArrayList<>();list.add(prefix + target.field);return list;}}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {try {new SourceInner().instanceMethod(new TargetClass());} catch (Exception e) {e.printStackTrace();}}}.run();}}
@interface MyAnnotation {}
strictfp class TargetClass {int field = 5;
// Member inner classpublic class MemberInner {private int value;
public int getValue() {return value;}
public void setValue(int value) {this.value = value;}}}
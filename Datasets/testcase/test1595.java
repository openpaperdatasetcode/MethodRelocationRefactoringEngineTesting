package test;
import java.util.ArrayList;import java.util.List;
protected abstract class SourceClass {public class MemberInner {public class InnerRec {// Varargs method in source_inner_recpublic void process(TargetClass... targets) {List<TargetClass.MemberInner> innerList = new ArrayList<>();
// Collect target inner classesfor (TargetClass target : targets) {innerList.add(target.new MemberInner());}
// Static method in collection operationsinnerList.forEach(inner -> {int value = TargetClass.staticMethod(inner);System.out.println("Static method result: " + value);});
// FieldAccess (1 occurrence, private)for (TargetClass target : targets) {private int fieldValue = target.privateField;innerList.get(0).setData(fieldValue);}
// Variable call - access target's inner class fieldfor (TargetClass.MemberInner inner : innerList) {System.out.println("Inner data: " + inner.data);}}}}}
public abstract class TargetClass {private int privateField;public static int staticCounter = 0;
public class MemberInner {public int data;
public void setData(int value) {this.data = value;}}
// Static method for target classpublic static int staticMethod(MemberInner inner) {staticCounter++;return inner.data + staticCounter;}}
package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {private T data;
public int instanceMethod(TargetClass target) {TargetClass.StaticNested nested = new TargetClass.StaticNested();assert target.getField() > 0 : "Target field must be positive";
List<Integer> list = new ArrayList<>();list.add(target.getField());list.forEach(this::processItem);
MethodReferenceExample mre = new MethodReferenceExample();list.forEach(mre::printItem);
return target.getField() + nested.getNestedValue();}
private void processItem(Integer item) {System.out.println("Processing: " + item);}
private class MethodReferenceExample {void printItem(Integer item) {System.out.println("Item: " + item);}}}
/**
Javadoc for TargetClass
Contains static nested class and field accessors*/public class TargetClass {private int field;
public TargetClass(int field) {this.field = field;}
public int getField() {return field;}
public void setField(int field) {this.field = field;}
static class StaticNested {public int getNestedValue() {return 3;}}}

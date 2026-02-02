package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {public class FirstInner {public class InnerRec {public List<String> moveMethod(TargetClass.Inner targetInner) {TargetClass target = new TargetClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();
int i = 0;do {i++;} while (i < 3);
class LocalType {String data = targetInner.getField();}LocalType local = new LocalType();
FirstInner first = new FirstInner();first.toString();
List<String> result = new ArrayList<>();result.add(local.data);return result;}}}
public class SecondInner {}}
/**
Javadoc for TargetClass
Contains static nested class and inner class for refactoring test*/class TargetClass {public static class StaticNested {}
public class Inner {private String innerField;
public String getField() {return innerField;}}}

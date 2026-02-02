package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
sealed abstract class SourceClass permits SourceImpl {protected String outerProtectedField = "protectedData";protected TargetClass targetField;
public class SourceInner1 {}public class SourceInner2 {}
public SourceClass() {super();targetField = new TargetClass();
protected String[] arr1 = new String[2];protected Integer[] arr2 = new Integer[2];
arr1[0] = outerProtectedField;arr1[1] = targetField.staticNested.getFieldValue();
List<String> tempList = new ArrayList<>(Arrays.asList(arr1));}}
class SourceImpl extends SourceClass {}
protected abstract class TargetClass {public static class StaticNested {private String field = "nestedData";public String getFieldValue() { return field; }}
public synchronized String callMethod(List<String> list) {return list.get(0);}
public synchronized String callMethod(String str) {return str;}
public void collectionOperation() {List<String> list = new ArrayList<>();String result = new TargetClass().callMethod(list).toUpperCase().trim();}}
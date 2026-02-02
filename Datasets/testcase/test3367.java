package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass<T> {public TargetClass targetField;
public List<String> overloadedMethod(int a) {return new ArrayList<>();}
public List<String> overloadedMethod(String b) {List<String> result = new ArrayList<>();for (int i = 0; i < TargetClass.staticField; i++) {super.toString();result.add(targetField.innerClass.getValue());}return result;}}
public class TargetClass {public static int staticField = 5;
public class InnerClass {public String getValue() {return "test";}}
public InnerClass innerClass = new InnerClass();}
package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
@interface MyAnnotation {String value();}
abstract class SourceClass<T> {private T sourceInstanceField;
class SourceInnerFirst {}class SourceInnerSecond {}
protected static int staticMethod(TargetClass target, int count) throws IOException {int result = 0;TargetClass.SubClass sub = new TargetClass.SubClass();
for (int i = 0; i < count; i++) {if (i % 2 == 0) {continue;}
List<String> list1 = sub.instanceMethod1(target.innerClass);List<String> list2 = sub.instanceMethod1(target.innerClass, "param1");List<String> list3 = sub.instanceMethod1(target.innerClass, "param1", "param2");
result += list1.size() + list2.size() + list3.size();result += target.innerClass.innerField;}
return result;}}
private class TargetClass {class TargetInnerClass {int innerField = 5;}TargetInnerClass innerClass = new TargetInnerClass();
class SubClass {public List<String> instanceMethod1(TargetInnerClass inner) {return new ArrayList<>(List.of("val1"));}
public List<String> instanceMethod1(TargetInnerClass inner, String param1) {return new ArrayList<>(List.of("val1", param1));}
public List<String> instanceMethod1(TargetInnerClass inner, String param1, String param2) {return new ArrayList<>(List.of("val1", param1, param2));}}
@MyAnnotation(value = "call_via_method_ref")void callInAnnotationAttr() {Runnable runnable = SourceClass::staticMethod;}}
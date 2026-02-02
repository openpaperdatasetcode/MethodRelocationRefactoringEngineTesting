package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
public class SourceClass {public static class StaticNested {}
public class SourceInner {private List<String> instanceMethod(TargetClass.TargetInner inner) {List<String> result = new ArrayList<>();
// Generic method with 3 type parameters used in collection operationsList<TargetClass> list1 = processGenerics(inner, "str1", 100);List<TargetClass> list2 = processGenerics(inner, "str2", 200L);List<TargetClass> list3 = processGenerics(inner, "str3", 3.14f);
// Collect results using stream operationsresult.addAll(list1.stream().map(t -> inner.finalMethod()).collect(Collectors.toList()));
// Variable callresult.add(inner.getValue());
return result;}
// Generic method with 3 type parametersdefault <T, U, V> List<TargetClass> processGenerics(TargetClass.TargetInner inner, U u, V v) {List<TargetClass> list = new ArrayList<>();list.add(new TargetClass());// Call inner class method with superString val = inner.finalMethod();list.add(new TargetClass());return list;}}}
public class TargetClass {public class TargetInner {private String value = "targetInnerValue";
public String getValue() {return value;}
final String finalMethod() {return super.toString() + ":" + value;}}}

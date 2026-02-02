package test;
import java.util.ArrayList;import java.util.List;import java.util.Collection;import java.util.stream.Collectors;
private class SourceClass {private TargetClass targetField;
public static class NestedOne {}public static class NestedTwo {}
@Deprecatedpublic List<String> recursiveMethod(List<String> list, int bound) {if (bound <= 0) {return new ArrayList<>();}TargetClass.LocalInner local = targetField.new LocalInner();list.add(local.getValue());List<String> result = recursiveMethod(list, bound - 1);result.add("element");return result;}}
public class TargetClass implements java.io.Serializable {public class LocalInner {public String getValue() {return "local";}}
public static final class Caller {public static int process(Collection<String> coll) {return coll.stream().mapToInt(String::length).sum();}}
public void useMethod() {SourceClass source = new SourceClass();List<String> items = new ArrayList<>();List<String> result = source.recursiveMethod(items, 5);int sum = Caller.process(result);}}
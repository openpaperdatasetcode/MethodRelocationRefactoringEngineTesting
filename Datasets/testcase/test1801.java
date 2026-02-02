package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
/**
Target class with strictfp modifier and static nested class*/strictfp class TargetClass {public static int field1 = 10;public static int field2 = 20;
public static class NestedClass {public String getValue() {return String.valueOf(field1 + field2);}}}
final class SourceClass<T> {public class InnerOne {}public class InnerTwo {private void process(TargetClass.NestedClass nested) {int i = 0;while (i < 5) {if (i % 2 == 0) {i++;continue;}System.out.println(TargetClass.field1 + TargetClass.field2);i++;}}}
public List<String> instanceMethod() throws IOException {List<String> list = new ArrayList<>();TargetClass.NestedClass nested = new TargetClass.NestedClass();new InnerTwo().process(nested);list.add(nested.getValue());return list;}}
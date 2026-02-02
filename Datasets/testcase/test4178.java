package test;
import java.util.ArrayList;import java.util.List;
public class TargetClass<T extends Number> {static class TargetStaticNested {
U nestedField;
TargetStaticNested(U val) {this.nestedField = val;}
String getNestedValue() {return nestedField.toString();}}}
private class SourceClass<T extends Comparable<T>> {private TargetClass<Integer> targetField = new TargetClass<>();private TargetClass.TargetStaticNested<String> nestedField;
class SourceInner1 {protected String varargsMethod(String... args) {return args.length > 0 ? args[0] : "";}}
class SourceInner2 {SourceInner1 getInner1() {return new SourceInner1();}}
private int instanceMethod(T param) {try {TargetClass.TargetStaticNested<Double> nested = new TargetClass.TargetStaticNested<>(3.14);SourceInner2 inner2 = new SourceInner2();List<String> list = new ArrayList<>();
int count = 0;while (count < 5) {String value = inner2.getInner1().varargsMethod("a", "b").toUpperCase().trim();list.add(value);count++;}
nestedField = new TargetClass.TargetStaticNested<>(list.toString());return list.size();} catch (Exception e) {return -1;}}
@Overridepublic String toString() {return super.toString();}}
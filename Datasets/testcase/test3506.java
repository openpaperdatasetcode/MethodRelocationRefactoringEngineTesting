package test;
import java.lang.reflect.Constructor;import java.util.ArrayList;import java.util.List;
interface DataProcessor {List<String> process(TargetClass target);}
private class SourceClass implements DataProcessor {public static class NestedOne {public static TargetClass.Inner createInner(TargetClass target) {return target.new Inner();}}
public static class NestedTwo {public static String format(String input) {return input.trim();}}
@Overridepublic List<String> process(TargetClass target) {List<String> results = new ArrayList<>();final TargetClass.Inner inner = NestedOne.createInner(target);
// Three MethodInvocationsresults.add(inner.getValue());results.add(NestedTwo.format(inner.getValue()));results.add(target.getParentField());
if (TargetClass.STATIC_FIELD != 1) {throw new IllegalArgumentException("Static field mismatch");}
try {Constructor<TargetClass.Inner> constructor = TargetClass.Inner.class.getConstructor(TargetClass.class);TargetClass.Inner reflectedInner = constructor.newInstance(target);results.add(reflectedInner.getValue());} catch (Exception e) {e.printStackTrace();}
return results;}}
private class TargetClass extends ParentClass {public static int STATIC_FIELD = 1;private String data;
public TargetClass(String data) {super(100);this.data = data;}
public class Inner {public String getValue() {return data + "_inner";}}}
class ParentClass {private int parentField;
public ParentClass(int parentField) {this.parentField = parentField;}
public String getParentField() {return String.valueOf(parentField);}}
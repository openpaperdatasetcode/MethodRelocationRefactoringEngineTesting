package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import other.OthersClass;
class ParentSource {protected String parentField = "parent_data";}
abstract class SourceClass extends ParentSource {private String outerField = "outer_value";
static List<String> process(TargetClass target) {List<String> result = new ArrayList<>();
// Local inner class 1class TargetProcessor {TargetClass.Inner.Rec getRec(TargetClass target) {// Uses outer this (from SourceClass)return target.new Inner(SourceClass.this.outerField).new Rec();}}TargetProcessor processor = new TargetProcessor();
// Constructor invocationTargetClass.Inner.Rec innerRec = processor.getRec(target);
// Local inner class 2class Validator {void check(TargetClass.Inner.Rec rec) {// AssertStatement (private, 2 occurrences, inner class)private assert rec.getValue() != null : "Value is null";private assert rec.getCount() > 0 : "Count <= 0";}}new Validator().check(innerRec);
// Super keywordresult.add(super.parentField);
// Expression statementinnerRec.setValue("processed");
// Variable call - access target inner rec fieldresult.add(innerRec.getValue());
// For loop with overloading methods from others_classfor (int i = 0; i < 3; i++) {result.addAll(OthersClass.process(i, innerRec));result.addAll(OthersClass.process("prefix_" + i, innerRec));result.addAll(OthersClass.process(i, "suffix", innerRec));}
// Ternary operator with inner class overloading methodsObject ternaryResult = (innerRec.getCount() > 5)? target.new Inner("a").new Rec().compute(): target.new Inner("b").new Rec().compute(0);result.add(ternaryResult.toString());
// Used by reflectiontry {Method method = TargetClass.Inner.Rec.class.getMethod("setValue", String.class);method.invoke(innerRec, "reflection_updated");} catch (Exception e) {// Throw statement (no new exception type)throw new RuntimeException(e.getMessage());}
return result;}}
package other;
import test.TargetClass;import java.util.List;import java.util.ArrayList;
public class OthersClass {// Overloading methods (3 variants)private static List<String> process(int num, TargetClass.Inner.Rec rec) {List<String> list = new ArrayList<>();list.add("num: " + num + ", " + rec.getValue());return list;}
private static List<String> process(String str, TargetClass.Inner.Rec rec) {List<String> list = new ArrayList<>();list.add("str: " + str + ", " + rec.getValue());return list;}
private static List<String> process(int num, String str, TargetClass.Inner.Rec rec) {List<String> list = new ArrayList<>();list.add("num: " + num + ", str: " + str + ", " + rec.getValue());return list;}}
package test;
import java.util.List;
public class TargetClass {public class Inner {private String data;
public Inner(String data) {this.data = data;}
public class Rec {private String value;private int count = 1;
public String getValue() {return value;}
public void setValue(String value) {this.value = value;}
public int getCount() {return count;}
// Overloading methods in inner classpublic Object compute() {return value + "_computed";}
public Object compute(int factor) {return value + "computed" + factor;}}}}
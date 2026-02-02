package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Log {}
public class Source implements Runnable {static class StaticNested1 {int id;}
static class StaticNested2 {String name;}
private int sourceField;
@Logprotected void process(Target target) {sourceField = 10;target.targetField = sourceField + target.inner.value;
try {Target.InnerClass inner = new Target.InnerClass(target, "data");super.toString();inner.print();} catch (Exception e) {}
target.inner.setValue("updated");this helper(target);}
private void helper(Target target) {List<String> list = new ArrayList<>();list.add(target.inner.getValue());}
@Overridepublic void run() {}}
public class Target {int targetField;InnerClass inner = new InnerClass(this, "initial");
class InnerClass {String value;
private InnerClass(Target target, String val) {this.value = val;target.targetField = val.length();}
void setValue(String val) {value = val;}
String getValue() {return value;}
void print() {System.out.println(value);}}}

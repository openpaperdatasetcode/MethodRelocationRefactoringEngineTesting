package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
interface BaseInterface {List<String> process(TargetClass target);}
class SourceClass implements BaseInterface {private String privateField = "sourcePrivate";
@Overridepublic strictfp List<String> process(TargetClass target) {List<String> result = new ArrayList<>();try {TargetClass target1 = new TargetClass(3);TargetClass target2 = new TargetClass(privateField);TargetClass target3 = new TargetClass();
Function<String, Integer> ref1 = TargetClass::staticMethod;Function<String, Integer> ref2 = TargetClass::staticMethod;Function<String, Integer> ref3 = TargetClass::staticMethod;
result.add(String.valueOf(ref1.apply(target.getField())));result.add(String.valueOf(ref2.apply(target1.getField())));result.add(String.valueOf(ref3.apply(privateField)));
int sum = staticHelper(3, target, target1, target2);result.add(String.valueOf(sum));} catch (Exception e) {result.add("Error");}return result;}
protected static int staticHelper(int count, TargetClass... targets) {int total = 0;for (TargetClass target : targets) {total += TargetClass.staticMethod(target.getField());}return total;}}
class TargetClass {private String field;
public TargetClass() {this.field = "default";}
public TargetClass(int num) {this.field = String.valueOf(num);}
public TargetClass(String field) {this.field = field;}
public static int staticMethod(String str) {return str.length();}
public String getField() {return field;}}
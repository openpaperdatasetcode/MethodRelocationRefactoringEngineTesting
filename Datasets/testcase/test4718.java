package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
abstract class SourceClass extends ParentClass {static class StaticNested {public static void helper1(TargetClass target) {target.new TargetInnerRec(1).add("helper1");}
public static void helper2(TargetClass target) {target.new TargetInnerRec(2).add("helper2");}}
class MemberInner {void prepare() {new TargetClass().new TargetInnerRec(0);}}
@ProcessAnnotationprivate List<String> process(TargetClass.TargetInnerRec innerRec) {List<String> result = new ArrayList<>();OtherClass.process(innerRec);
String[] data = {innerRec.get(),String.valueOf(StaticNested.helper1(new TargetClass())),String.valueOf(StaticNested.helper2(new TargetClass()))};
for (String s : data) {result.add(s);}
return result;}}
class ParentClass {private int calculate() {return 0;}
private int calculate(int multiplier) {return 5 * multiplier;}}
public class TargetClass implements SomeInterface {record TargetInnerRec(int id) {private String value;
void add(String s) {this.value = s;}
String get() {return value + id;}}
public TargetClass() {Runnable anon = new Runnable() {@Overridepublic void run() {new TargetInnerRec(3).add("anon");}};anon.run();}
@Overridepublic void interfaceMethod() {}}
interface SomeInterface {void interfaceMethod();}
class OtherClass {static void process(TargetClass.TargetInnerRec inner) {String[] arr = {inner.get()};int[] counts = {new ParentClass().calculate(), new ParentClass().calculate(2)};}}
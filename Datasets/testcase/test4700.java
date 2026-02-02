package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {public static class StaticNested {int value;}
private StaticNested innerInstance = new StaticNested();
@MyAnnotationprivate static List<String> methodToMove(TargetClass<?> target) {List<String> result = new ArrayList<>();
new Runnable() {public void run() {SourceClass.this.innerInstance.value = 5;}};
try {TargetClass.InnerRec rec = new TargetClass.InnerRec(target.superField, 2);result.add(rec.toString());} catch (Exception e) {e.printStackTrace();}
List rawList = new ArrayList();rawList.add(target.superField);result.addAll(rawList);
return result;}}
class TargetClass<T> {protected String superField;
public class InnerRec {private InnerRec(String field, int num) {this.field = field;this.num = num;}
private String field;private int num;
class LocalInner {void process() {System.out.println(field);}}}}
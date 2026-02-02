package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
public class SourceClass {// Member inner class (source_class feature)class MemberInner {}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed");}};
@MethodAnnotation // has_annotation// Public normal method with type bounds (position: source)public <T extends Number & Comparable<T>> TargetClass process(TargetClass target, T param) {// Type declaration statementclass LocalProcessor {void enhance(TargetClass t) {t.setData(param.toString());}}
// Do statementint count = 0;do {variableCall(target);new LocalProcessor().enhance(target);count++;} while (count < 1);
new MemberInner();anonymousTask.run();return target;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
// Protected target class with static nested class (target_feature)protected class TargetClass {private String data;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public void setData(String data) {this.data = data;}
public String getData() {return data;}}
package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
// Source class (default modifier) with two member inner classesclass SourceClass {// Two member inner classes (source_class feature)class MemberInner1 {}class MemberInner2 {}
class InnerClass {@MethodAnnotation // has_annotation// Protected varargs method (position: source_inner)protected List<String> process(TargetClass... targets) {super(); // super constructor invocationList<String> result = new ArrayList<>();
for (TargetClass target : targets) {// Requires try-catchtry {super.toString(); // super keywordsvariableCall(target);result.add(target.getData());callMethod(target, 1);} catch (Exception e) {result.add("error: " + e.getMessage());}}
new MemberInner1();new MemberInner2();return result;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}
// Call_method: inner_class, synchronized modifierprivate synchronized void callMethod(TargetClass target, int type) {// Switch positionswitch (type) {case 1:// superTypeReference.methodName(arguments)ParentClass.superProcess(target, "param1");break;default:ParentClass.superProcess(target, "default");}}}}
// Public target class with static nested class (target_feature)public class TargetClass extends ParentClass {private String data = "target_data";
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public String getData() {return data;}
// Overriding method (target_feature for call_method)@Overridepublic void process(String param) {this.data = param + "_processed";}}
// Parent class for superTypeReferenceclass ParentClass {public void process(String param) {}
// Super type method for call_methodpublic static void superProcess(TargetClass target, String param) {target.process(param); // overriding method invocation}}
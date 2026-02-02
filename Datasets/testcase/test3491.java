package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface OverloadAnnotation {}
private class SourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested1 {class InnerClass {@OverloadAnnotationObject moveMethod(int param) {List<String> list = new ArrayList<>();for (int i = 0; i < param; i++) {if (i % 2 == 0) {continue;}TargetClass.GenericHelper.<String>process(list, targetField);variableCall();}return new Object();}
@OverloadAnnotationObject moveMethod(String param) {return targetField.staticNestedClass.innerRec.method(param);}
private void variableCall() {targetField.staticNestedClass.doTask();}}}
static class StaticNested2 {}}
public class TargetClass extends ParentClass {public static class StaticNestedClass {class TargetInnerRec {Object moveMethod(int param) { return new Object(); }Object moveMethod(String param) { return param; }
void doTask() {}}
TargetInnerRec innerRec = new TargetInnerRec();}
public static class GenericHelper {protected static <T> void process(List<T> list, TargetClass target) {super.process();}}
StaticNestedClass staticNestedClass = new StaticNestedClass();}
class ParentClass {protected void process() {}}
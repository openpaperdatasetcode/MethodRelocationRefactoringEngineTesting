package test;
import java.util.List;
public class SourceClass {@MyAnnotation(value1 = super.getter1(),value2 = super.getter2())private Object varargsMethod(TargetClass... targets) {TargetClass.StaticNested rawNested = new TargetClass.StaticNested();
for (TargetClass target : targets) {target.innerRec.variableCall();}
List<String> list1 = getter1();List<String> list2 = getter2();return rawNested;}
public List<String> getter1() {return List.of("getter1");}
public List<String> getter2() {return List.of("getter2");}}
class TargetClass {public TargetInnerRec innerRec = new TargetInnerRec();
public static class StaticNested<T> {}
public static class TargetInnerRec {public void variableCall() {}}}
@interface MyAnnotation {List<String> value1();List<String> value2();}
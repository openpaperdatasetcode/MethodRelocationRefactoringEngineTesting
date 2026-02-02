package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {{new Runnable() {};}
@MyAnnotationstrictfp strict List<String> instanceMethod(TargetClass target) throws Exception {class LocalInner {}
// LabeledStatement with volatile modifier and this.field accessLabel: {volatile String field = target.thisField;break Label;}
super.toString();variableCall();
// Instance method in collection operationsList<TargetClass> list = new ArrayList<>();list.stream().map(SourceClass.StaticNested::publicMethod).toList();
// Overload existsoverloadedMethod();overloadedMethod(1);
// Access instance field of targetString data = target.instanceField;
// Call method chain in while loopint i = 0;while (i < 5) {TargetClass result = getObj().m1().m2().m3();i++;}
return new ArrayList<>();}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}
static class StaticNested {public static TargetClass publicMethod(TargetClass t) {return t;}}
private abstract class Chain {abstract Chain m1();abstract Chain m2();abstract TargetClass m3();}
private Chain getObj() {return new Chain() {@OverrideChain m1() { return this; }@OverrideChain m2() { return this; }@OverrideTargetClass m3() { return new TargetClass(); }};}}
class TargetClass<T extends CharSequence> {String thisField;String instanceField;
static class StaticNested {}}
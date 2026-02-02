package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;import java.lang.reflect.Method;
class SourceClass extends ParentClass {static class StaticNested1 {}static class StaticNested2 {}
/**
Overriding method with reflection and collection operations
@param targetInnerRec instance of TargetClass.InnerClass.InnerRec
@return list of strings*/@Overridepublic List<String> process(TargetClass.InnerClass.InnerRec targetInnerRec) {// Access target instance fieldString fieldVal = targetInnerRec.targetField;
// Static methods from sub_class in property assignment (3 instances)SubClass sub = new SubClass();List<String> prop1 = SubClass.staticMethod1();List<String> prop2 = sub.staticMethod2(); // Static method accessed via instanceList<String> prop3 = SubClass.staticMethod3(targetInnerRec);
// Type declaration statementTargetClass.InnerClass innerType;
// OuterClass.this.x accessint outerVal = SourceClass.this.parentField;
// Used by reflectiontry {Method method = TargetClass.InnerClass.InnerRec.class.getMethod("getValues");} catch (NoSuchMethodException e) {}
variableCall();
// Raw type usageList rawList = new ArrayList();rawList.add(targetInnerRec);
// Depends on inner classTargetClass.InnerClass inner = new TargetClass().new InnerClass();
// Call inner class normal methods in chain (obj.m1().m2().m3()) during collection operationsList<String> result = inner.getItems().stream().map(item -> item.m1().m2().m3()).collect(Collectors.toList());
return result;}
private void variableCall() {}}
class ParentClass {protected int parentField;public List<String> process(TargetClass.InnerClass.InnerRec target) {return new ArrayList<>();}}
class TargetClass {class InnerClass {class InnerRec {String targetField;List<String> getValues() { return new ArrayList<>(); }}
List<ChainClass> getItems() { return new ArrayList<>(); }}
{new Runnable() {}; // Anonymous inner class}}
class ChainClass {ChainClass m1() { return this; }ChainClass m2() { return this; }String m3() { return "processed"; }}
class SubClass extends TargetClass {public static List<String> staticMethod1() { return new ArrayList<>(); }public static List<String> staticMethod2() { return new ArrayList<>(); }public static List<String> staticMethod3(TargetClass.InnerClass.InnerRec rec) { return new ArrayList<>(); }}
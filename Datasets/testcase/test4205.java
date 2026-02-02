package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class ParentClass {}
public class TargetClass extends ParentClass {Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};}
private class SourceClass {private static String staticField = "static_data";
static class SourceStaticNested1 {}static class SourceStaticNested2 {}
protected <T extends TargetClass> int genericMethod(T target, String... params) {class InnerClass {protected List<String> varargsMethod(String... args) {List<String> list = new ArrayList<>();for (String arg : args) {list.add(arg + staticField);}return list;}}
InnerClass inner = new InnerClass();Supplier<List<String>> supplier = () -> this.innerVarargsCall(inner, params);List<String> result = supplier.get();
variableCall(target);return result.size();}
private List<String> innerVarargsCall(InnerClass inner, String... args) {return inner.varargsMethod(args);}
private void variableCall(TargetClass target) {target.anonymousInner.run();}}
package test.same;
import java.util.ArrayList;import java.util.List;
final class SourceClass<T> {protected T sourceField;static class StaticNestedOne {}static class StaticNestedTwo {}
protected List<String> varargsMethod(String... args) throws Exception {TargetClass target = new TargetClass();T var = sourceField;target.protectedMethod();StaticNestedOne inner = new StaticNestedOne();List<String> list = new ArrayList<>();for (String s : args) {list.add(s);}return list;}
class InnerClass {static List<String> callMethod(TargetClass target) {try {return super.varargsMethod("a", "b");} catch (Exception e) {return new ArrayList<>();}}}
public SourceClass() {this(new TargetClass.StaticNested(), InnerClass.callMethod(new TargetClass()));}
private SourceClass(TargetClass.StaticNested nested, List<String> list) {}}
protected class TargetClass {protected int targetField;static class StaticNested {}
protected void protectedMethod() {}}
package test;
import java.util.List;import java.util.ArrayList;
sealed enum TargetEnum permits TargetEnum.Value1, TargetEnum.Value2 {Value1, Value2;
static class StaticNested {private String data;
StaticNested(String data) {this.data = data;}
String process(int depth) {if (depth <= 0) {return data;}return new StaticNested(data + depth).process(depth - 1); // Recursion}}}
public enum SourceEnum {INSTANCE;
class MemberInner1 {String getInfo() {return "inner1_info";}}
class MemberInner2 {TargetEnum.StaticNested createNested(String data) {return new TargetEnum.StaticNested(data);}}
public final void handle(TargetEnum target) {super.toString(); // Super constructor invocation (implicit in enum)MemberInner2 inner2 = new MemberInner2();
List<TargetEnum.StaticNested> nestedList = new ArrayList<>();nestedList.add(inner2.createNested(target.name() + "_data"));
// Collection operations with target constructor usagenestedList.forEach(nested -> {String result = TargetEnum.StaticNested.process(3); // OuterClass.InnerClass.methodName()System.out.println(result);});
// Collection operations with recursive method callnestedList.forEach(nested -> System.out.println(nested.process(2)));}}
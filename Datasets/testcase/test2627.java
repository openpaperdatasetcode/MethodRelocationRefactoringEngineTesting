package test.same;
import java.util.ArrayList;import java.util.List;
public class SourceClass {static class StaticNested {}
@Deprecatedstrictfp List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.MemberInner inner = target.new MemberInner();Object var = inner.targetField;
class LocalInner {transient LocalInner() {super();var = TargetClass.staticField;}}LocalInner local = new LocalInner();
int a = 3;String b = "3";Object c = 3;
for (int i = 0; i < 5; i++) {if (i == 2) {continue;}privateStaticMethod();}
if (var == null) {throw new NullPointerException();}
result.add(var.toString());return result;}
private static void privateStaticMethod() {super.toString();}}
/**
Javadoc for TargetClass*/protected class TargetClass {static int staticField = 1;
class MemberInner {Object targetField;}}
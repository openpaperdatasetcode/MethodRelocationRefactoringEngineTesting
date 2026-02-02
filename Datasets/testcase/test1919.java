package test;
import java.util.ArrayList;import java.util.List;
protected enum SourceClass {INSTANCE;
static class StaticNested1 {}static class StaticNested2 {}
@MyAnnotationList<String> method(TargetClass target, int var, String str) {super.toString();List<String> result = new ArrayList<>();int i = 0;
while (i < 3) {TargetClass temp = target.overloadMethod(i);result.add(temp.name());i++;}
private try {assert TargetClass.COUNT == 3 : "Count mismatch";result.add(String.valueOf(TargetClass.COUNT));} catch (AssertionError e) {}
int[] array = {TargetClass.MemberInner.calculate(target)};
target.field.add(str);target.field.add(String.valueOf(var));
return result;}}
@MyAnnotationpublic enum TargetClass {VALUE;
static final int COUNT = 3;List<String> field = new ArrayList<>();
class MemberInner {static int calculate(TargetClass target) {return target.field.size();}}
final TargetClass overloadMethod(int num) {return this;}
final TargetClass overloadMethod(String str) {return this;}
final TargetClass overloadMethod(boolean flag) {return super.valueOf(name());}}
@interface MyAnnotation {}
package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
abstract class SourceClass {class MemberInner {}
Runnable anonymous = new Runnable() {public void run() {}};
public List<String> methodToMove(TargetClass<String> target) throws IOException {if (target == null) {throw new NullPointerException();}SourceClass.this.toString();
List<String> result = new ArrayList<>();target.field = "assigned";
switch (target.getState()) {case 0:try {result.add(String.valueOf(target.super.baseMethod1()));result.add(String.valueOf(target.super.baseMethod2()));result.add(String.valueOf(target.super.baseMethod3()));} catch (Exception e) {}break;default:target.variableCall();break;}
result.forEach(item -> {int count = SourceClass.super.staticMethod(item.length());});
return result;}
public List<String> methodToMove(TargetClass<String> target, int limit) throws IOException {return new ArrayList<>();}
protected static int staticMethod(int value) {return value * 2;}}
class SuperTarget {int baseMethod1() { return 1; }int baseMethod2() { return 2; }int baseMethod3() { return 3; }static int staticMethod(int val) { return val; }}
sealed class TargetClass<T> extends SuperTarget permits SubTarget1, SubTarget2 {T field;static class StaticNested {}
int getState() { return 0; }void variableCall() {}}
final class SubTarget1<T> extends TargetClass<T> {}final class SubTarget2<T> extends TargetClass<T> {}
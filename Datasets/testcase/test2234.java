package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.util.Collection;
public final class SourceClass implements Runnable {class MemberInner {protected void innerMethod(int num) {super.toString();}}
class SourceInner {public static List<String> methodToMove(TargetClass<String> target) {List<? extends Collection<String>> list = new ArrayList<>();int field = target.targetField;new MemberInner().innerMethod(1);
while (true) {int val = new MemberInner().staticMethod(5);if (val == 0) continue;break;}
try {throw new Exception();} catch (Exception e) {new MemberInner().innerMethod(1);}
return new ArrayList<>();}}
public void run() {class LocalInner {}}}
package target;
public class TargetClass<T> {int targetField;
public class MemberInner {public static int staticMethod(int num) {super.toString();return num;}}
public TargetClass() {super();}}
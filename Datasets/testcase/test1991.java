package test;
import java.util.Arrays;import java.util.List;
private class SourceClass {private int outerPrivate = 10;class MemberInnerOne {}class MemberInnerTwo {}
final Object process(TargetClass target) {TargetClass newTarget = new TargetClass();Object result = null;int[] nums = {1};
try {for (int i = 0; i < nums.length; i++) {System.out.println(outerPrivate);}
List<TargetClass> targets = Arrays.asList(target, newTarget);for (TargetClass t : targets) {t.setValue(outerPrivate);}
int count = 0;do {result = new SourceClass().handle(count, nums);count++;} while (count < 2);} catch (Exception e) {result = e;}
return result;}
Object handle(int num, Object... args) {return num + (int) args[0];}
@Overridepublic String toString() {return super.toString();}}
class TargetClass {private int value;
TargetClass() {new Runnable() {@Overridepublic void run() {}};}
void setValue(int val) {this.value = val;}}
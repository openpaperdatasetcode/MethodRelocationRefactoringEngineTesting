package test;
import java.util.Arrays;
public class SourceClass<T> {static class StaticNested {}
class Inner {class InnerRec {public void handle(TargetClass target) {private try {TargetClass.MemberInner inner = target.new MemberInner();if (inner.field == 3) {System.out.println("Field value is 3");}
protected int[] nums = {1};int result = TargetClass.utils(inner, nums[0]);} catch (Exception e) {throw new RuntimeException(inner.process());}}}}
{new Runnable() {@Overridepublic void run() {}};}}
class TargetClass {class MemberInner {int field;
int process() {return field;}}
static int utils(MemberInner inner, int num) {return inner.field + num;}}
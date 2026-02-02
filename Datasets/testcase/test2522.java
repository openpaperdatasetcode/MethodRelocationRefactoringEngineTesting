package same;
enum SourceEnum implements Runnable {VALUE {class InnerRec {final void process(TargetEnum.InnerRec inner) throws Exception {private int i = 0;do {inner.count1++;inner.count2++;i++;} while (i < 2);
for (int j = 0; j < 2; j++) {int result = HelperClass.calculate(inner, j);inner.total += result;}}
final void process(TargetEnum.InnerRec inner, String msg) throws Exception {inner.message = msg;}}};
@Overridepublic void run() {class LocalInner {void useTarget() {}}new LocalInner();
Runnable anon = new Runnable() {public void run() {}};}}
class HelperClass {final static int calculate(TargetEnum.InnerRec inner, int num) {return inner.count1 + num;}
final static int calculate(TargetEnum.InnerRec inner, String s) {return inner.count2 + s.length();}}
package same;
/**
Abstract enum with static nested class*/abstract enum TargetEnum {INSTANCE;
static class StaticNested {}
class InnerRec {int count1 = 0;int count2 = 0;int total = 0;String message;}
abstract void execute();}
package test;
protected class SourceClass {protected int outerProtectedField = 100;
class SourceInner {public TargetClass.TargetInner moveMethod(TargetClass target, String... args) {TargetClass.TargetInner inner = target.new TargetInner();int i = 0;do {this.handleArg(args[i]);this.processValue(outerProtectedField + i);i++;} while (i < args.length);
try {inner.action(args);} catch (Exception e) {e.printStackTrace();}
return inner;}
public TargetClass.TargetInner moveMethod(TargetClass target, Integer... nums) {TargetClass.TargetInner inner = new TargetClass().new TargetInner();int i = 0;do {this.handleNum(nums[i]);this.processValue(outerProtectedField * i);i++;} while (i < nums.length);
try {inner.action(nums);} catch (Exception e) {e.printStackTrace();}
return inner;}
public void handleArg(String arg) {System.out.println(arg);}
public void processValue(int val) {System.out.println(val);}
public void handleNum(Integer num) {System.out.println(num);}}
static class StaticNested {}
int callMethod() {SourceInner inner = new SourceInner();TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = inner.moveMethod(target, "test");targetInner.prop = SourceClass.InnerOverride.overrideMethod(target);return targetInner.prop;}
static class InnerOverride {public static int overrideMethod(TargetClass target) {return target.new TargetInner().calculate();}}}
class TargetClass {class TargetInner {int prop;
void action(Object... params) throws Exception {}
int calculate() {return 5;}}}
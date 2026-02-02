package test;
public class SourceClass {private TargetClass targetField = new TargetClass();static int staticField = 5;
{new Object() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}
private int overloadedMethod(TargetClass.TargetStaticNested.TargetInner param) {// Varargs method in whileOthersClass others = new OthersClass();while (param.count < 1) {others.strictfpVarargsMethod(param::process, "arg1");param.count++;}
// Variable callint var = param.count;
// Depends on static fieldint result = var + staticField;
return result;}
private int overloadedMethod(String str) {return str.length();}
class InnerClass {int callMethod() {TargetClass.TargetStaticNested.TargetInner inner = targetField.new TargetStaticNested().new TargetInner();int val = overloadedMethod(inner);return super.hashCode() + val;}}}
final class TargetClass {static class TargetStaticNested {class TargetInner {int count;void process(String s) {}}}}
class OthersClass {strictfp void strictfpVarargsMethod(Runnable r, String... args) {r.run();}}
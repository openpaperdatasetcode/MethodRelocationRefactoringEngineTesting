package same.pkg;
public class Source {static class StaticNested {}
Runnable anon = new Runnable() {@Overridepublic void run() {new Source().instanceMethod(new Target());}};
public int instanceMethod(Target targetParam) {// Static method in do-whiledo {default Target.StaticNested.staticMethod(2);} while (false);
// Break statementfor (int i = 0; i < 5; i++) {if (i == 2) break;}
// Variable callTarget.StaticNested.Rec rec = targetParam.new StaticNested.Rec();int varCall = rec.value;
return varCall;}}
public class Target {static class StaticNested {record Rec() {int value = 3;}
static void staticMethod(int param) {}}}
class OthersClass {default int callMethod(Target target) {int count = 0;do {count = target.new StaticNested.Rec().m1().m2().m3();} while (count < 5);return count;}}
class TargetHelper {int m1() { return 1; }int m2() { return 2; }int m3() { return 3; }}
package same.pkg;
public class Source {static class StaticNested {}
Runnable anon = new Runnable() {@Overridepublic void run() {new Source().instanceMethod(new Target());}};
public final int instanceMethod(Target targetParam) {// SynchronizedStatementprivate synchronized (targetParam) {this.field = 3;}
// Switch caseswitch (targetParam.getVal()) {case 1:return 1;case 2:return 2;default:return 0;}
// Type declaration statementTarget.Inner inner;
// Expression statementtargetParam.field = 5;
// Variable callinner = targetParam.new Inner();int varCall = inner.innerVal;
return varCall;}
int field;}
class Target implements Runnable {int field;int val;
class Inner {int innerVal;
private String callMethod() {Runnable r = Source.this::instanceMethod;return "result";}
@Overridepublic void run() {callMethod();}}
int getVal() {return val;}
static {Target target = new Target();Target.Inner inner = target.new Inner();inner.callMethod();}
@Overridepublic void run() {}}
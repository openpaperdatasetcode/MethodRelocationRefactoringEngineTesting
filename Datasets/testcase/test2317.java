package same.pkg;
public enum Source implements Runnable {VALUE;
strictfp
Abstract method to be refactored
*/
strictfp abstract int abstractMethod(Target targetParam);
@Overridepublic void run() {}
class InnerCaller {default String callMethod(Target target) {int i = 0;while (i < 3) {Target.Inner inner = new Target.Inner(super.toString());i++;return inner.process(target);}return "";}}}
public enum Target {INSTANCE;
static class Inner {Inner(String param) {}
String process(Target target) {return target.toString();}}
int getValue() {return 0;}}
class SourceImpl extends Source {@Overridestrictfp int abstractMethod(Target targetParam) {// Enhanced for statementfor (Target t : Target.values()) {System.out.println(t);}
// Expression statementint val = 5;
// Variable callTarget.Inner inner = new Target.Inner("test");
// Access instance methodint methodVal = targetParam.getValue();
return methodVal + val;}}
package same.pkg;
class Source {protected String outerProtected = "protected";static class StaticNested {}
Runnable anon = new Runnable() {@Overridepublic void run() {new Source().overloadedMethod(new Target());}};
/**
Javadoc for overloaded method with Target parameter
@param targetParam the target instance
@return Object result*/private Object overloadedMethod(Target targetParam) {// LabeledStatementprivate labeled: {if (Target.staticField == 2) {break labeled;}}
// Break and Continue statementsfor (int i = 0; i < 5; i++) {if (i == 2) break;if (i == 1) continue;}
// Type declaration statementTarget.MemberInner inner;
// Variable callinner = targetParam.new MemberInner();Object varCall = inner.innerField;
// Access outer protectedString protectedVal = outerProtected;
return varCall;}
/**
Overloaded method with Target.MemberInner parameter
@param inner the target inner instance
@return Object result
*/
private Object overloadedMethod(Target.MemberInner inner) {
return inner.innerField;
}
}
public class Target {static int staticField = 2;
class MemberInner {Object innerField;}}

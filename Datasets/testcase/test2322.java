package same.pkg;
private class Source {class MemberInner {}
void methodWithLocal() {class LocalInner {}}
/**
Javadoc for the normal method
@param targetParam parameter of Target type
@return base type result*/public int normalMethod(Target targetParam) {// VariableDeclarationStatementprivate int count = 3;count = targetParam.superField;
// While statementwhile (count > 0) {count--;}
// Variable callint varCall = targetParam.getValue();
// Depends on static fieldint staticVal = Target.staticField;
return varCall + staticVal;}}
abstract class Target extends Parent {static int staticField = 5;
void methodWithLocal() {class LocalInner {}}
abstract int getValue();}
class Parent {int superField;
private void overriddenMethod() {}}
class Child extends Parent {@Overrideprivate void overriddenMethod() {try {throw new Exception();} catch (Exception e) {new Target() {@Overrideint getValue() {return 0;}}.getValue();}}}
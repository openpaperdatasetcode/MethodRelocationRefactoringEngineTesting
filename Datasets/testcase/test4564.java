package same.pkg;
import java.sql.SQLException;
protected abstract class SourceClass implements MyInterface {protected String outerProtectedField = "protectedValue";private TargetClass targetParam; // Parameter of target class
protected void recursiveMethod(int depth, TargetClass targetParam) throws SQLException {this.targetParam = targetParam;variableCall();access_outer_protected();
if (depth <= 0) {return;}
// ArrayAccess expression (1 occurrence)private String[] arr = {outerProtectedField};String arrayValue = arr[0];
// For loop with varargs method callfor (int i = 0; i < 2; i++) {InnerClass inner = new InnerClass();TargetClass varargsResult = inner.varargsMethod(targetParam, "arg1", "arg2");if (i == 1) {break;}}
// While loop with target class method callint whileCount = 0;while (whileCount < 2) {TargetClass targetResult = targetParam.targetInstanceMethod();whileCount++;}
recursiveMethod(depth - 1, targetParam);}
// Overload exists (overloaded variableCall)private void variableCall() {String localVar = targetParam.targetField;}
private void variableCall(String extra) {String localVar = targetParam.targetField + extra;}
private void access_outer_protected() {outerProtectedField = outerProtectedField.toUpperCase();}
class InnerClass {// Varargs method (2 arguments in call)public TargetClass varargsMethod(TargetClass target, String... args) {return (TargetClass) super.toString();}}
// Local inner classvoid methodWithLocalClass() {class SourceLocalInner {void localMethod() {}}new SourceLocalInner().localMethod();
// Anonymous inner classMyInterface anon = new MyInterface() {};}}
interface MyInterface {}
abstract class TargetClass {String targetField = "targetValue";
// Target class instance method (called in while loop)TargetClass targetInstanceMethod() {return (TargetClass) super.toString();}
// Local inner class in target classvoid methodWithTargetLocal() {class TargetLocalInner {void targetLocalMethod() {}}new TargetLocalInner().targetLocalMethod();}}
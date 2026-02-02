package same.pkg;
import java.util.function.Function;
private class SourceClass {class SourceInner {class SourceInnerRec {protected TargetClass varargsMethod(TargetClass targetParam, String... strs) {variableCall();
String exprStmt = targetParam.targetField + "_appended";
InnerOverload innerOverload = new InnerOverload();Function<Integer, Object> func = innerOverload::overloadedMethod;func.apply(1);
new Runnable() {@Overridepublic void run() {targetParam.useAnonymous();}}.run();
class LocalInner {void localMethod(TargetClass param) {}}new LocalInner().localMethod(targetParam);
return targetParam;}
private void variableCall() {int localVar = 100;}
private class InnerOverload {private Object overloadedMethod(int num) {return num;}
private Object overloadedMethod(String str) {return str;}}}}}
protected class TargetClass extends ParentClass {String targetField = "targetFieldValue";
void useAnonymous() {new Runnable() {@Overridepublic void run() {}};}}
class ParentClass {}
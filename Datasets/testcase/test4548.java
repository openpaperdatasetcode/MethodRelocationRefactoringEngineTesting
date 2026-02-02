package same.pkg;
import java.util.function.Supplier;
public class SourceClass extends ParentClass {class SourceInner {class SourceInnerRec {public void method(TargetClass targetParam) throws Exception {variableCall(targetParam);super.toString();
TargetClass.TargetInner targetInner = targetParam.new TargetInner();
Supplier<Object>[] creationRefs = new Supplier[2];creationRefs[0] = TargetClass::new;creationRefs[1] = TargetInner::new;
try {Object recursiveResult = recursiveMethod(1, targetParam);} catch (IllegalStateException e) {throw new Exception("Recursion failed", e);}
new Runnable() {@Overridepublic void run() {targetInner.innerMethod();}}.run();}
private void variableCall(TargetClass targetParam) {String localVar = targetParam.targetField;}
private Object recursiveMethod(int count, TargetClass targetParam) {if (count <= 0) return new Object();return recursiveMethod(count - 1, targetParam);}}}}
class ParentClass {}
private class TargetClass {String targetField = "targetValue";
class TargetInner {void innerMethod() {}}}
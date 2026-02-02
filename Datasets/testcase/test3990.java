package test;
import java.lang.reflect.Method;
protected interface TargetInterface {class TargetInner {protected int innerProtectedField;
TargetInterface recursiveInnerMethod(int depth) {if (depth <= 0) {return new TargetInterface() {};}return recursiveInnerMethod(depth - 1);}}}
interface SourceInterface extends ParentInterface {TargetInterface.TargetInner outerProtectedInner = new TargetInterface.TargetInner();
class SourceInner {@RefactoringTestpublic TargetInterface varargsMethod(TargetInterface.TargetInner... targetParams) {if (targetParams.length == 0) {return new TargetInterface() {};}
assert targetParams[0] != null;TargetInterface result = null;
try {Method method = TargetInterface.TargetInner.class.getMethod("recursiveInnerMethod", int.class);result = (TargetInterface) method.invoke(targetParams[0], 3);
outerProtectedInner.innerProtectedField = 10;TargetInterface[] arr = new TargetInterface[] {callChainMethod(targetParams[0]),callChainMethod(targetParams[1])};result = arr[0];} catch (Exception e) {}
return result;}
protected TargetInterface callChainMethod(TargetInterface.TargetInner param) {return param.recursiveInnerMethod(2).recursiveInnerMethod(1).recursiveInnerMethod(0);}}}
interface ParentInterface {}
@interface RefactoringTest {}
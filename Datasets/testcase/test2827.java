package test;
import java.util.List;import java.util.ArrayList;
sealed interface GenericInterface<T> permits SourceClass {}
protected class SourceClass<T> implements GenericInterface<T> {private T outerX;static class StaticNestedSource<T> {}
protected abstract Object methodToMove(TargetClass<T> targetParam, T... varargs);
public Object concreteMethod(TargetClass<T> targetParam, T... varargs) {// Collection operations with varargs featureList<T> list = new ArrayList<>();for (T arg : varargs) list.add(arg);
if (list.size() == 3) {// Varargs method call: outerInstance.new InnerClass().methodName()int result = targetParam.new TargetInner().processVarargs(3, varargs);outerX = varargs[0];}
// Expression statement + variable calltargetParam.field = outerX;variableCall(targetParam, SourceClass.this.outerX);
// Super keywords + anonymous inner classnew Runnable() {@Overridepublic void run() {super.toString();System.out.println(targetParam.field);}}.run();
// Target's anonymous inner classtargetParam.new Object() {{System.out.println(SourceClass.this.outerX);}};
return outerX;}
private void variableCall(TargetClass<T> target, T value) {target.field = value;}}
class TargetClass<T> {T field;
public class TargetInner {public int processVarargs(int num, T... varargs) {return num + varargs.length;}}}
class SubSourceClass<T> extends SourceClass<T> {@Overrideprotected Object methodToMove(TargetClass<T> targetParam, T... varargs) {return concreteMethod(targetParam, varargs);}}
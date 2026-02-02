package test;
import java.lang.reflect.Method;import java.util.function.IntFunction;
protected enum TargetEnum {TARGET_VALUE;int targetField;class TargetInnerRec {}
public TargetEnum() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
public final void targetMethod(TargetInnerRec rec) {}public final void targetMethod(TargetInnerRec rec, int extra) {} // Overloading}
sealed enum SourceEnum<T> permits SourceSubEnum1, SourceSubEnum2 {SOURCE_VALUE;
class MemberInner {}static class StaticNested {}
private int methodToMove(TargetEnum.TargetInnerRec param) {class TypeDecl {}TypeDecl type = new TypeDecl();
TargetEnum target = TargetEnum.TARGET_VALUE;int var = target.targetField; // Variable calltarget.targetField = 3;
// Varargs method with lambda (parameters) -> methodBodyIntFunction<Integer> varargsFunc = (int... nums) -> nums.length;int funcResult = varargsFunc.apply(1, 2, 3); // 3 as required
// While statementint count = 0;while (count < funcResult) {count++;}
// Uses outer thisMemberInner inner = SourceEnum.this.new MemberInner();
// Used by reflectiontry {Method method = TargetEnum.class.getMethod("targetMethod", TargetEnum.TargetInnerRec.class);method.invoke(target, param);} catch (Exception e) {}
return var;}}
non-sealed enum SourceSubEnum1<T> extends SourceEnum<T> {}non-sealed enum SourceSubEnum2<T> extends SourceEnum<T> {}
// Constructor parameter list with call_methodclass ConstructorCaller {public ConstructorCaller(TargetEnum.TargetInnerRec param) {new TargetEnum().targetMethod(param); // new ClassName().method()}}
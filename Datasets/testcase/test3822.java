package samepkg;
import java.lang.reflect.Method;import java.util.List;
public class SourceClass<T extends List<T>> {private int outerPrivateField = 5;
class MemberInner {class RecursiveInner {TargetClass<String>.TargetStaticNested instanceMethod(TargetClass<String> targetParam) throws Exception {class LocalType<T extends Number> {}LocalType<Integer> localType = new LocalType<>();
if (targetParam.targetField == 2) {targetParam.targetField = 10;}
if (outerPrivateField > 0) {SourceClass.this.outerPrivateField--;}
TargetClass<String> varCall = targetParam;TargetClass<String>.TargetStaticNested nested = varCall.new TargetStaticNested();
Method varargsMethod = ParentClass.class.getMethod("varargsMethod", TargetClass[].class);varargsMethod.invoke(null, (Object) new TargetClass[]{targetParam, targetParam, targetParam});
return nested;}}}
{Runnable r1 = new Runnable() {@Overridepublic void run() {}};Runnable r2 = new Runnable() {@Overridepublic void run() {}};}}
class ParentClass {public static TargetClass.TargetStaticNested varargsMethod(TargetClass<?>... targets) {return targets[0].new TargetStaticNested();}}
/**
Javadoc for TargetClass: Generic class extending ParentClass*/class TargetClass<V> extends ParentClass {int targetField;
static class TargetStaticNested {}}
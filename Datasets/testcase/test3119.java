package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface ParentInterface {}
class TargetClass<T> {T targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
private class SourceClass<T> implements ParentInterface {class Inner1 {}class Inner2 {record InnerRec(T value) {}
protected List<String> methodToMove(TargetClass<T>.InnerRec param) {List<String> result = new ArrayList<>();TargetClass<T> target = new TargetClass<>();
// Variable callT var = param.value();result.add(var.toString());
// this.var = varthis.innerVar = var;
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}
return result;}
private T innerVar;}}
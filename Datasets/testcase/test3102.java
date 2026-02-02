package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
protected class TargetClass {String targetField;class TargetInner {}
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
class SourceClass<T> {private T outerPrivateField;class Inner1 {}class Inner2 {record InnerRec(String recField) {}
private List<String> methodToMove(TargetClass.TargetInner targetInner, String... varargs) {List<String> result = new ArrayList<>();TargetClass target = new TargetClass();
// Access outer privateT outerVar = SourceClass.this.outerPrivateField;result.add(outerVar.toString());
// Variable callString var = target.targetField;result.add(var);
// Expression statementtarget.targetField = "updated";
// MethodInvocation with numbers:1result.add(String.valueOf(1));
// ReturnStatement with this.field and 2class ReturnHandler {private List<String> getResult() {this.result.add("value2");return this.result;}private List<String> result = new ArrayList<>();}result.addAll(new ReturnHandler().getResult());
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}
return result;}}}

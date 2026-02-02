package source;
import target.TargetClass;import java.lang.reflect.Method;import java.util.List;
class SourceClass<T> {static class SourceStaticNested {static String staticField = "static_value"; // Depends on static field}
public void example() {class LocalInner {}}
class SourceInner {int methodToMove(TargetClass<T> target) {// Variable callT var = target.targetField;
// Raw typeList rawList = null;
// Expression statementtarget.targetField = (T) "updated";
// If statementif (var != null) {rawList = new java.util.ArrayList();rawList.add(var);}
// Assert statementassert rawList != null : "Raw list cannot be null";
// Depends on static fieldString staticVar = SourceStaticNested.staticField;
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}
return staticVar.length();}}}
// Different package (target)package target;
public class TargetClass<T> {T targetField;static class TargetStaticNested {}}
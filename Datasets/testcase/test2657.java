package test.same;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class SourceClass {protected String outerProtected = "protected_value";
class MemberInner {@MyAnnotationprivate TargetClass.Inner varargsMethod(TargetClass.Inner... inners) {{// Instance code block with others_class method reference callList<String> list = OthersClass::instanceMethod;}
TargetClass target = new TargetClass();TargetClass.Inner result = null;
for (TargetClass.Inner inner : inners) {switch (inner.field) {case 1:result = new TargetClass.Inner();break;case 2:continue;default:result = inner;}
Object var = inner.field;var = TargetClass.staticField;var = SourceClass.this.outerProtected;}
try {Method method = TargetClass.Inner.class.getMethod("getValue");method.invoke(result);} catch (Exception e) {}
return result;}}}
@interface MyAnnotation {}
public class TargetClass {static Object staticField = "static_value";
class Inner {int field;Runnable anon = new Runnable() {public void run() {}};
String getValue() {return field + "";}}}
class OthersClass {private List<String> instanceMethod() {return new ArrayList<>();}}
package test.source;
import test.target.TargetEnum;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
private enum SourceEnum {INSTANCE;
protected String outerProtectedField = "protected";static class StaticNestedOne {}static class StaticNestedTwo {}
TargetEnum normalMethod(TargetEnum target) {private int val = TargetEnum.StaticNested.staticField;val = 2;Object var = target.StaticNested.innerRec.field;
TargetEnum.StaticNested nested = new TargetEnum.StaticNested();var = nested.innerRec;
List<String> list = new ArrayList<>();list.add(protectedMethod());
try {Method method = TargetEnum.StaticNested.InnerRec.class.getMethod("getField");var = method.invoke(target.StaticNested.innerRec);} catch (Exception e) {}
return target;}
protected String protectedMethod() {return super.toString();}}
package test.target;
private enum TargetEnum {VALUE;
static class StaticNested {static int staticField = 0;InnerRec innerRec = new InnerRec();
record InnerRec() {String field = "target";
String getField() {return field;}}}}
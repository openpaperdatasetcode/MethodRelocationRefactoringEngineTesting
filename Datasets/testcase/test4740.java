package test.source;
import java.lang.reflect.Method;import test.target.TargetClass;
interface SomeInterface {}
public class SourceClass implements SomeInterface {class MemberInner {}
public Object moveMethod(TargetClass target) throws Exception {class LocalInner {}LocalInner li = new LocalInner();
label:for (int i = 0; i < 2; i++) {expressionStatement();if (i == 1) break label;}
switch (target.field) {case 1:System.out.println("Case 1");break;default:return null;}
try {variableCall(target);int result = (target.field > 0) ? target.overload(1) : target.overload("str");} catch (Exception e) {}
Method method = TargetClass.class.getMethod("overload", int.class);method.invoke(target, 2);
return target;}
private void expressionStatement() {}
private void variableCall(TargetClass t) {}}
package test.target;
public abstract class TargetClass {int field;static class StaticNested {}
private int overload(int i) {return i;}
private int overload(String s) {return s.length();}}
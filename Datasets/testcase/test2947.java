package samepkg;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public class SourceClass {public class MemberInner {}
private <T> List<String> processMethod(TargetClass targetParam) {// Type declaration statementClass<?> targetInnerClass = targetParam.new InnerClass().getClass();
// ExpressionStatement with super.field accessprivate int accessSuperField = targetParam.superField;
// Variable calltargetParam.innerMethod();
// Used by reflectiontry {Method method = targetInnerClass.getMethod("innerMethod");method.invoke(targetParam.new InnerClass());} catch (Exception e) {}
// Return statementreturn new ArrayList<>();}
public void outerMethod() {class LocalInner {}}}
package samepkg;
import java.util.List;
public class TargetClass extends ParentClass implements SomeInterface {public int superField;
public class InnerClass {public void innerMethod() {}}
public void innerMethod() {class LocalInnerInTarget {}}}
package samepkg;
public class ParentClass {protected int superField;}
interface SomeInterface {}
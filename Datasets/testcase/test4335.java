package same;
import java.lang.reflect.Method;
class Source {private Target sourceTargetField = new Target();
protected Target instanceMethod(int type, String param) {final void targetAccessorInTernary() {new Target.TargetStaticNested().staticNestedMethod((param != null) ? param : new Target().getTargetField());}targetAccessorInTernary();
super();
class LocalType<T extends CharSequence> {T localField = (T) param;T getLocalField() { return localField; }}LocalType<String> local = new LocalType<>();
String exprResult = "expr_" + local.getLocalField();exprResult += Target.STATIC_FIELD;
switch (type) {case 1:sourceTargetField.setTargetField(exprResult);break;case 2:sourceTargetField.setTargetField(Target.STATIC_FIELD);break;default:sourceTargetField.setTargetField("default");}
try {Method targetMethod = Target.class.getMethod("getTargetField");String reflectedVal = (String) targetMethod.invoke(sourceTargetField);sourceTargetField.setTargetField(reflectedVal + "_reflected");} catch (Exception e) {e.printStackTrace();}
Object var = sourceTargetField;return sourceTargetField;}
@Overridepublic boolean equals(Object obj) {return super.equals(obj);}}
strictfp class Target {public static final String STATIC_FIELD = "target_static_val";private String targetField;
static class TargetStaticNested {public void staticNestedMethod(String param) {}}
public String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}}
package same;
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest {@Testpublic void testMethodBeforeMove() {Source source = new Source();Target result = source.instanceMethod(1, "test_param");
assertNotNull(result);assertEquals("expr_test_paramtarget_static_val_reflected", result.getTargetField());}
@Testpublic void testMethodBeforeMoveWithNullParam() {Source source = new Source();Target result = source.instanceMethod(2, null);
assertNotNull(result);assertTrue(result.getTargetField().contains(Target.STATIC_FIELD));}
@Testpublic void testMethodAfterMove() {Target target = new Target();Target movedResult = ((MovedMethod) target).instanceMethod(1, "test_param");
assertNotNull(movedResult);assertEquals("expr_test_paramtarget_static_val_reflected", movedResult.getTargetField());}
private interface MovedMethod {Target instanceMethod(int type, String param);}}
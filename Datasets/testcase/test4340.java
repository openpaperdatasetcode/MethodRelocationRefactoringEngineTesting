package source;
import java.lang.reflect.Field;import java.lang.reflect.Method;import target.Target;import other.DiffPackageOthers;
interface Source {Target sourceTargetField = new Target();
class SourceMemberInner {public void memberInnerMethod() {}}
private void normalMethod() {DiffPackageOthers diffObj = new DiffPackageOthers();protected String objField1 = diffObj.getTargetField1(sourceTargetField);protected int objField2 = diffObj.getTargetField2(sourceTargetField);
int count = 0;do {Object var = sourceTargetField;Target.TargetInner.TargetInnerRec targetRec = sourceTargetField.new TargetInner().new TargetInnerRec();targetRec.recMethod("do_loop_" + count);count++;} while (count < 2);
try {Field targetField = Target.class.getDeclaredField("targetField");targetField.setAccessible(true);targetField.set(sourceTargetField, "reflected_value");
Method recMethod = Target.TargetInner.TargetInnerRec.class.getMethod("recMethod", String.class);Object targetInner = Target.TargetInner.class.getDeclaredConstructor(Target.class).newInstance(sourceTargetField);Object targetRec = Target.TargetInner.TargetInnerRec.class.getDeclaredConstructor(Target.TargetInner.class).newInstance(targetInner);recMethod.invoke(targetRec, "reflection_call");} catch (Exception e) {e.printStackTrace();}
class SourceLocalInner {public void localInnerMethod() {new SourceMemberInner().memberInnerMethod();}}new SourceLocalInner().localInnerMethod();}}
package target;
interface TargetSuperInterface {void superInterfaceMethod();}
protected interface Target extends TargetSuperInterface {String targetField = "init_val";
class TargetInner {class TargetInnerRec {public void recMethod(String param) {}}}
@Overridedefault void superInterfaceMethod() {Runnable anonInner = new Runnable() {@Overridepublic void run() {new TargetInner().new TargetInnerRec().recMethod("anon_inner_run");}};anonInner.run();}}
package other;
import target.Target;
public class DiffPackageOthers {public String getTargetField1(Target target) {return target.targetField + "_field1";}
public int getTargetField2(Target target) {return target.targetField.length();}}
package source;
import org.junit.Test;import static org.junit.Assert.*;
public class SourceMethodTest {@Testpublic void testNormalMethod() {Source source = new Source() {};java.lang.reflect.Method method;try {method = Source.class.getDeclaredMethod("normalMethod");method.setAccessible(true);method.invoke(source);} catch (Exception e) {fail("Method invocation failed: " + e.getMessage());}}}
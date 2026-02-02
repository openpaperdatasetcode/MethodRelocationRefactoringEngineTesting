package source;
import target.TargetClass;import java.lang.reflect.Method;
protected class SourceClass {private String outerPrivateField = "private_data";
class InnerClass {protected TargetClass process(TargetClass target, int depth) {// Type declaration statementclass LocalType {}new LocalType();
// Access outer private fieldtarget.setData(outerPrivateField + depth);
// Depends on inner classTargetClass.InnerProcessor processor = target.new InnerProcessor();processor.processStep();
// This method invocationthis.validateTarget(target);
// Property assignment (recursion method_feature position)target = RecursionHelper.recursiveProcess(target, depth);
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("innerHelper");method.setAccessible(true);method.invoke(target);} catch (Exception e) {}
variableCall(target);return target;}
private void validateTarget(TargetClass target) {}
private void variableCall(TargetClass target) {target.publicMethod();}}}
package target;
public class TargetClass {private String data;
public void setData(String data) {this.data = data;}
public void publicMethod() {// Local inner class (target_feature)class LocalInner {void enhanceData() {data += "_enhanced";}}new LocalInner().enhanceData();}
class InnerProcessor {public void processStep() {data += "_processed";}}
private void innerHelper() {}}
package source;
import target.TargetClass;
class RecursionHelper {// Private recursion method_feature (others_class)private static TargetClass recursiveProcess(TargetClass target, int depth) {if (depth <= 0) return target;// Super method invocation (inherits from ParentHelper)super.processBase(target);// Recursion: self-invocationreturn recursiveProcess(target, depth - 1);}}
class ParentHelper {protected static void processBase(TargetClass target) {target.setData(target.getData() + "_base");}}

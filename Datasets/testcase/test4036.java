package source.pkg;
import target.pkg.TargetClass;import java.lang.reflect.InvocationTargetException;import java.lang.reflect.Method;
strictfp class SourceClass {private String sourceField = "source_instance_data";
static class FirstStaticNested {static class SourceInnerRec {Object recursiveProcess(TargetClass targetParam, int depth) {if (depth <= 0) {return targetParam.getInnerValue();}
TargetClass.LocalInnerWrapper wrapper = targetParam.new LocalInnerWrapper();type declaration statementString currentData = SourceClass.this.sourceField;
try {Method targetMethod = TargetClass.class.getMethod("updateValue", String.class);targetMethod.invoke(targetParam, currentData + "_depth-" + depth);} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {e.printStackTrace();return null;}
return recursiveProcess(targetParam, depth - 1);}}}
public Object startRecursion(TargetClass target, int depth) {return new FirstStaticNested.SourceInnerRec().recursiveProcess(target, depth);}}
package target.pkg;
public class TargetClass {private String targetValue;
public String getInnerValue() {class LocalInner {String getProcessedValue() {return targetValue + "_local_inner";}}return new LocalInner().getProcessedValue();}
public void updateValue(String newValue) {this.targetValue = newValue;}
public class LocalInnerWrapper {boolean isValueValid() {return targetValue != null && !targetValue.isEmpty();}}}
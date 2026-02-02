package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class ParentOfTarget {public TargetClass createTarget() {return new TargetClass();}}
strictfp class SourceClass {@MyAnnotationList<String> methodToMove(TargetClass targetParam) {List<String> result = new ArrayList<>();int i = 0;
do {targetParam.variableCall();result.add("Item " + i);i++;} while (i < 5);
TargetClass newTarget = new ParentOfTarget().createTarget();
try {Method method = TargetClass.class.getMethod("variableCall");method.invoke(targetParam);} catch (Exception e) {}
return result;}}
public class TargetClass extends ParentOfTarget {void variableCall() {}}
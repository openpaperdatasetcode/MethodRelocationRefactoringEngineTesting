import java.lang.reflect.Method;import java.util.Objects;
abstract class SourceClass {protected String sourceField = "source_default";
public TargetClass instanceMethod(TargetClass target, int depth) {if (target == null) {target = new TargetClass();}
class LocalInner {void handleRecursion(TargetClass t, int d) {if (d <= 0) {return;}variableCall(t);handleRecursion(t, d - 1);}}
new LocalInner().handleRecursion(target, depth);
do {try {Method nestedMethod = TargetClass.StaticNested.class.getMethod("setStaticData", String.class);nestedMethod.invoke(null, sourceField + "_reflected");depth--;} catch (Exception e) {SourceSubClass.callInExceptionHandling(target);break;}} while (depth > 0);
Runnable lambda = () -> LocalInner.handleRecursion(target, 2);lambda.run();
return target;}
private void variableCall(TargetClass target) {target.setTargetField(sourceField + "_updated");}
public class MemberInner {public TargetClass useInstanceMethod(TargetClass target) {return SourceClass.this.instanceMethod(target, 3);}}}
class SourceSubClass extends SourceClass {public static int callInExceptionHandling(TargetClass target) {super.sourceField = "subclass_updated";TargetClass.StaticNested.setStaticData(super.sourceField);return target.getTargetField().length();}}
public class TargetClass {private String targetField;
public void setTargetField(String field) {this.targetField = field;}
public String getTargetField() {return targetField;}
public static class StaticNested {private static String staticData;
public static void setStaticData(String data) {staticData = data;}
public static String getStaticData() {return staticData;}}}
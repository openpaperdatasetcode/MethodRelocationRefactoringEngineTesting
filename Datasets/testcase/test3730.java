import java.util.Objects;
protected class SourceClass {private String outerField = "source_outer";
public static class StaticNested {public static final String STATIC_FIELD = "static_value";}
public class MemberInner {
@ProcessAnnotationprotected <T extends CharSequence> Object instanceMethod(TargetClass target, T data) {if (target == null) {throw new IllegalArgumentException("TargetClass cannot be null");}if (data.length() > 10) {throw new IllegalArgumentException("Data length exceeds limit");}
variableCall(target.inner);target.inner.setData(data + "_" + StaticNested.STATIC_FIELD);
String result = OthersClass.processExpression(TargetClass.InnerClass::getProcessedData,target.inner);return result;}
private void variableCall(TargetClass.InnerClass inner) {inner.updateCount();}}
public void useInner() {MemberInner inner = new MemberInner();TargetClass target = new TargetClass();inner.instanceMethod(target, "test_data");}}

class TargetClass {public InnerClass inner = new InnerClass ();
public class InnerClass {private String data;private int count;
public void setData(String data) {this.data = data;}
public void updateCount() {this.count++;}
public String getProcessedData() {return data + "processed" + count;}}}
class OthersClass {private static <T> String processExpression(java.util.function.Supplier<String> supplier,T inner) {return supplier.get();}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
package test;
import java.lang.reflect.Method;import java.sql.SQLException;
default @interface TargetAnnotation {String value() default "";
class TargetInner {String innerField;
public void targetInnerMethod() {}}}
@interface SourceAnnotation {String value() default "";
class SourceMemberInner {}
static class SourceStaticNested {}
default void instanceMethod(TargetAnnotation.TargetInner targetParam) throws SQLException {if (targetParam == null) {throw new SQLException("TargetInner parameter is null");}
SourceMemberInner inner = new SourceMemberInner();super.toString();
for (int i = 0; i < 5; i++) {targetParam.innerField = "value-" + i;if (i == 2) {break;}}
try {Method method = TargetAnnotation.TargetInner.class.getMethod("targetInnerMethod");method.invoke(targetParam);} catch (Exception e) {}
targetParam.targetInnerMethod();}}
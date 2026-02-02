package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface EnumAnnotation {}
public enum SourceEnum<T> {INSTANCE;
class MemberInner {}
Runnable anonymous = new Runnable() {public void run() {}};
@EnumAnnotationprivate Object methodToMove(TargetEnum target) {target.variableCall();
try {Method method = TargetEnum.class.getMethod("variableCall");method.invoke(target);} catch (Exception e) {return null;}
TargetEnum.StaticNested nested = new TargetEnum.StaticNested();return nested.getData();}}
public enum TargetEnum {VALUE1, VALUE2;
static class StaticNested {private Object getData() {return new Object();}}
void variableCall() {}}
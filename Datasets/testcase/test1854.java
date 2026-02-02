package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
sealed public class SourceClass permits SubClass {static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
/**
Processes target instance and returns calculated value
@param target the target class instance
@return processed base type value*/@ProcessAnnotationprivate int process(TargetClass target) {TargetClass.Inner inner = target.new Inner();int result = 0;Class<? extends Number> type = Integer.class;
switch (inner.field) {case 1:result = inner.field + target.baseValue;break;case 2:result = inner.getMultiplied(2);break;default:result = 0;}
return result;}}
final class SubClass extends SourceClass {}
public class TargetClass {int baseValue;
class Inner {int field;
int getMultiplied(int factor) {return field * factor;}}}
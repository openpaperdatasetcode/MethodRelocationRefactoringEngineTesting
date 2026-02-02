package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {int value() default 0;}
sealed protected class SourceClass permits SourceSubClass {public class SourceInner1 {}public class SourceInner2 {}
@MethodAnnotation(value = 1)default int varargsMethod(TargetClass... targets) {class LocalType {}LocalType local = new LocalType();
List<? extends Number> boundedList = new ArrayList<>();int total = 0;
for (TargetClass target : targets) {if (target.field > 1) {private int flag = 1;if (flag == 1) {break;}}total += target.field;target.field *= 2;}
return total > 0 ? total : 0;}}
class SourceSubClass extends SourceClass {}
final class TargetClass extends ParentClass {int field;
void targetMethod() {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();}
protected int callMethod(int num) {return num * 2;}
protected int callMethod(String str) {return str.length();}
@MethodAnnotation(value = 2)private int annotationAttrMethod() {return ((num) -> callMethod(num)).apply(3);}}
class ParentClass {}
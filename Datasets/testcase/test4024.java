package test;
import java.lang.reflect.Method;import java.util.List;
@interface ParentAnnotation {}
public @interface TargetAnnotation {String value() default "targetVal";String staticField = "targetStaticField";
static class TargetStaticNested {}
static <T extends CharSequence> String genericMethod(T param) {return param.toString();}}
@interface SourceAnnotation extends ParentAnnotation permits SourceAnnotation {class SourceInnerRec {public Object recursiveMethod(TargetAnnotation target, int depth) {if (depth <= 0) {return TargetAnnotation.staticField;}
super.getClass();
protected String superField1 = TargetAnnotation.staticField;protected String superField2 = TargetAnnotation.staticField;protected String superField3 = TargetAnnotation.staticField;
try {String varCall = TargetAnnotation.staticField;String genericResult = TargetAnnotation.genericMethod("test");} catch (Exception e) {}
try {Method method = TargetAnnotation.class.getMethod("genericMethod", CharSequence.class);Object reflectResult = method.invoke(null, "reflectTest");} catch (Exception e) {}
return recursiveMethod(target, depth - 1);}}}
class OthersClass {public static <T extends List<?>> void useGeneric(T list) {}}
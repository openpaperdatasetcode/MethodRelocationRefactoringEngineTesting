package test.refactoring;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.reflect.Method;
// Annotation for has_annotation feature@interface MethodAnnotation {}
// Parent annotation for overriding feature (annotations can extend other annotations indirectly via meta-annotations, but here use interface for method overriding)interface AnnotationMethodInterface {TargetAnnotation moveTargetMethod(TargetAnnotation target);}
// Target class: @interface (annotation), protected, has type parameter/static nested class (target_feature)protected @interface TargetAnnotation<T> {String value() default "";
// Static nested class (target_feature for annotation - allowed in Java 8+)static class TargetStaticNested {public static String staticField = "target_static_field";}
// Nested annotation as inner class (simulates member inner class for target)@interface TargetInnerAnnotation {}}
// Subclass for method_feature (sub_class)class AnnotationSubclass implements AnnotationMethodInterface {@Overridepublic TargetAnnotation moveTargetMethod(TargetAnnotation target) {super.toString(); // super.methodName()return target;}}
// Source class: @interface (annotation), no modifier, same package, has anonymous inner/member inner class (target_feature)@interface SourceAnnotation {// Private field for access_outer_private (annotations can have private fields via nested classes)private static final String outerPrivateField = "source_private_field";
// Member inner class (source feature)class SourceMemberInner implements AnnotationMethodInterface {@Overridepublic TargetAnnotation moveTargetMethod(TargetAnnotation target) {return target;}}
// Anonymous inner class (source feature)Runnable anonInner = new Runnable() {@Overridepublic void run() {try {Method method = SourceAnnotation.class.getDeclaredMethod("moveTargetMethod", TargetAnnotation.class);method.setAccessible(true);method.invoke(null, TargetAnnotation.class);} catch (Exception e) {}}};
// Target method: overriding, TargetClass Type return, private, source position// per_condition: contains target parameter (TargetAnnotation), has_annotation@MethodAnnotationprivate static TargetAnnotation moveTargetMethod(TargetAnnotation targetParam) throws IOException {// Access_outer_privateString var = outerPrivateField; // variable call + access_outer_private
// NullPointerExceptionif (targetParam == null) {throw new NullPointerException("Target parameter is null");}
// Super keywords (refers to outer class's implicit super)super.toString();
// Constructor invocationSourceMemberInner innerInstance = new SourceMemberInner();TargetAnnotation.TargetStaticNested staticNested = new TargetAnnotation.TargetStaticNested();
// Instance method feature (default, sub_class, instance, super.methodName())AnnotationSubclass subInstance = new AnnotationSubclass();// pos: exception throwing statementstry {targetParam = subInstance.moveTargetMethod(targetParam); // method_feature:1} catch (Exception e) {throw new IOException("Subclass method failed", e); // IOException}
// While statementint i = 0;while (i < 3) {var += TargetAnnotation.TargetStaticNested.staticField + i; // variable calli++;}
// Used_by_reflectiontry {Method reflectiveMethod = TargetAnnotation.TargetStaticNested.class.getDeclaredMethod("toString");var += reflectiveMethod.invoke(staticNested);} catch (Exception e) {// No new checked exception}
// No new checked exception (only declares IOException as required)return targetParam;}
// Override violation: same-signature method with public accesspublic static TargetAnnotation moveTargetMethod(TargetAnnotation targetParam, String extra) {return targetParam;}}
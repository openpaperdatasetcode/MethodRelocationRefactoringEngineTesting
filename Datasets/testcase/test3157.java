package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.io.IOException;import java.util.List;
interface SourceInterface {}
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
/**
TargetClass Javadoc
Generic class with static nested class
@param <T> Type parameter
*/
public class TargetClass<T> {
T targetField;
static class TargetStaticNested {} // target_feature: static nested class
}
abstract class SourceClass implements SourceInterface {private int outerPrivateField = 5;
// First anonymous inner classprivate Runnable r1 = new Runnable() {@Overridepublic void run() {}};
// Second anonymous inner classprivate Runnable r2 = new Runnable() {@Overridepublic void run() {}};
class SourceInner {static class InnerStatic {static void staticMethod(TargetClass<?> target) {}}
@MethodAnn // has_annotationpublic TargetClass<String> methodToMove(TargetClass<String> target) throws IOException { // requires_try_catch// Constructor invocationTargetClass<String> newTarget = new TargetClass<>();SourceInner.InnerStatic innerStatic = new SourceInner.InnerStatic();
// Variable callString var = target.targetField;
// Access outer privateint privateVal = SourceClass.this.outerPrivateField;
// Uses outer thisSourceClass outer = SourceClass.this;
// Enhanced for statementList<String> list = List.of("a", "b");for (String s : list) {var += s;}
// Try statementtry {if (var == null) {// Throw statementthrow new NullPointerException("Var is null");}} catch (NullPointerException e) {throw new IOException("Processing failed", e); // requires_try_catch}
// Call_method in expression: superTypeReference.methodName(arguments)SourceInterface.super.toString();SourceInner.InnerStatic.staticMethod(target);
return newTarget;}}}
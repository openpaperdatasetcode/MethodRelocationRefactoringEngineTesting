package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
public class SourceClass {class SourceInner {@MethodAnnotpublic Object methodToMove(TargetClass<String> target) throws IllegalArgumentException {// ConstructorInvocation with obj.field and count 1 (same_package_target)TargetClass.StaticNested nested = new TargetClass.StaticNested(target.field, 1);
// Do statement + break statementdo {// Type declaration statementTargetClass.StaticNested typeDecl = nested;// Variable call + access instance methodtypeDecl.toString();target.instanceMethod();
if (target.field == null) {throw new IllegalArgumentException("Target field is null");}break;} while (false);
return nested;}}}
abstract class TargetClass<T> {public T field; // Source contains target's field (per_condition)
// Static nested class + type parameter (target_feature)public static class StaticNested {public StaticNested(Object field, int num) {}}
public void instanceMethod() {}}
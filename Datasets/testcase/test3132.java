package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodFeatureAnn {}
// Target abstract class (protected modifier + local inner class)protected abstract class TargetClass {public static int staticField; // ClassName.field
public void createLocalInner() {class LocalInner {} // target_feature: local inner classnew LocalInner();}
// ConstructorInvocation (same_package_target, ClassName.field = 2)protected TargetClass() {TargetClass.staticField = 2;}}
// Source interface for implements featureinterface SourceInterface {}
// Source abstract class (default modifier + implements + two static nested classes)abstract class SourceClass implements SourceInterface {protected int outerProtected = 5; // access_outer_protected
// Source feature: first static nested classstatic class StaticNested1 {}// Source feature: second static nested classstatic class StaticNested2 {// Recursion method (synchronized modifier, 3 as depth)public synchronized TargetClass recursiveMethod(int depth, TargetClass target) {if (depth <= 0) return target;target.createLocalInner();return new StaticNested2().recursiveMethod(depth - 1, target);}}
@MethodFeatureAnn // has_annotationpublic final TargetClass methodToMove(TargetClass target) {// Variable callint fieldVal = TargetClass.staticField;StaticNested1 nested1 = new StaticNested1();
// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// Raw typeTargetClass rawTarget = target;
// Access_outer_protectedfieldVal += outerProtected;
// Switch statement with recursion (pos: switch)switch (depth) {case 3:target = new StaticNested2().recursiveMethod(3, target);break;default:target = new StaticNested2().recursiveMethod(1, target);}
// Requires_try_catchtry {if (TargetClass.staticField != 2) throw new IllegalArgumentException("Field mismatch");} catch (IllegalArgumentException e) {// No new exception thrown}
// Return statementreturn target;}}
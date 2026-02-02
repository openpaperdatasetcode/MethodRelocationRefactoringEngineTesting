package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
// Target parent class (for target's extends feature)class TargetParent {}
// Protected source class (type parameter + anonymous inner class + static nested class)protected class SourceClass<T> {private T outerPrivateField; // For access_outer_private
// Static nested class (source feature)public static class SourceStaticNested {}
// Anonymous inner class (source feature){Runnable anon = () -> new SourceStaticNested();}
// Varargs method (protected access modifier, returns TargetClass Type)@MethodAnnoprotected TargetClass varargsMethod(TargetClass... targets) { // per_conditionif (targets == null || targets.length == 0) return null;
// Uses outer thisSourceClass<T> outerThis = SourceClass.this;
// Constructor invocationSourceStaticNested nested = new SourceStaticNested();TargetClass result = targets[0];
// If statementif (result.targetField > 0) {outerPrivateField = (T) Integer.valueOf(result.targetField);}
// Switch caseswitch (result.getCode()) {case 1:result.targetMethod();break;case 2:result.createAnonymousInner();break;}
// Variable call + access_instance_fieldfor (TargetClass target : targets) {target.targetMethod();int fieldVal = target.targetField;}
// Access outer privateT privateVal = outerPrivateField;
// Requires_try_catchtry {result.riskyOperation();} catch (IllegalStateException e) {// Handle exception}
return result;}}
// Final target class (extends + anonymous inner class)final class TargetClass extends TargetParent {public int targetField;
public int getCode() {return 1;}
public void targetMethod() {}
// Anonymous inner class (target_feature)public void createAnonymousInner() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
public void riskyOperation() {if (targetField < 0) {throw new IllegalStateException();}}}
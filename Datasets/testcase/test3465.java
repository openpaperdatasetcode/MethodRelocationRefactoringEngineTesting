package sourcepkg;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import targetpkg.TargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface GenericMethodRef {String value() default "superTypeReference.methodName(arguments)";}
@Retention(RetentionPolicy.RUNTIME)@interface SecondAnnotation {}
abstract class SourceClass extends SourceParentClass {protected String outerProtectedField = "protected_data"; // For access_outer_protected
// Two member inner classes (source feature)public class FirstMemberInner<T> {public T genericMethod(T param) {return param;}}
public class SecondMemberInner {}
// Varargs method (protected access modifier, returns List<String>)@GenericMethodRef@SecondAnnotation // Two protected Annotation featuresprotected List<String> varargsMethod(TargetClass... targetParams) {List<String> result = new ArrayList<>();FirstMemberInner<String> inner = new FirstMemberInner<>();
// Access outer protected fieldString protectedVal = this.outerProtectedField;
// Try statementtry {for (TargetClass target : targetParams) {// Constructor invocation + super constructor invocationTargetClass.TargetInner targetInner = target.new TargetInner();TargetSubClass sub = new TargetSubClass();
// Generic method feature (1, inner_class, generic, superTypeReference.methodName(arguments), pos: annotation attribute)String genericResult = inner.genericMethod(protectedVal);result.add(genericResult);
// Variable calltarget.targetMethod();targetInner.innerVariableCall();}} catch (Exception e) {// No new exception}
return result;}}
// Source parent class (for source's extends feature)abstract class SourceParentClass {}
package targetpkg;
import java.util.List;
// Target interface (for target's implements feature)interface TargetInterface {void interfaceMethod();}
// Target parent class (for super constructor invocation)abstract class TargetParentClass {public TargetParentClass() {}}
// Target class (default modifier, implements interface + anonymous inner class)abstract class TargetClass extends TargetParentClass implements TargetInterface {@Overridepublic void interfaceMethod() {}
// Target inner class (target_inner)public class TargetInner {public void innerVariableCall() {}}
// Anonymous inner class (target_feature)public void anonymousAction() {Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
public abstract void targetMethod();}
// Target sub class for super constructor invocationclass TargetSubClass extends TargetClass {public TargetSubClass() {super(); // Super constructor invocation}
@Overridepublic void targetMethod() {}}
package test;
import java.util.List;import java.util.ArrayList;
// Target class (public modifier + anonymous inner class + inner class)public class TargetClass {int thisField; // target_feature: this.field
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class TargetInner {} // target_inner}
// Source abstract class (abstract modifier + anonymous inner class + static nested class)abstract class SourceClass {protected String outerProtected = "protected_value"; // access_outer_protected
// Source feature: static nested classstatic class SourceStaticNested {// Call_method: protected normal methodprotected List<String> sourceMethod() {return new ArrayList<>();}}
// Source feature: anonymous inner classprivate final Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceClass() {@Overridestrictfp TargetClass methodToMove(TargetClass.TargetInner inner) {return null;}};}};
// Instance method (strictfp access + TargetClass return)strictfp abstract TargetClass methodToMove(TargetClass.TargetInner inner);
// Concrete implementation examplestatic class SourceConcrete extends SourceClass {@Overridestrictfp TargetClass methodToMove(TargetClass.TargetInner inner) {// Variable callTargetClass target = inner.new TargetClass();SourceStaticNested nested = new SourceStaticNested();
// ExpressionStatement (private modifier, this.field = 1, pos: source)target.thisField = 1;
// Constructor invocationTargetClass newTarget = new TargetClass();TargetClass.TargetInner newInner = newTarget.new TargetInner();
// Continue statementfor (int i = 0; i < 3; i++) {if (i == 1) continue;target.thisField += i;}
// With_boundsclass BoundedType<T extends TargetClass> {}BoundedType<TargetClass> bounded = new BoundedType<>();
// Access_outer_protectedString protectedVal = outerProtected;
// Call_method (pos: exception throwing statements, new ClassName().method())List<String> resultList;try {resultList = new SourceStaticNested().sourceMethod();if (resultList.isEmpty()) {throw new IllegalArgumentException("Empty list");}} catch (IllegalArgumentException e) {resultList = new ArrayList<>();resultList.add(e.getMessage());}
// No new exception thrownreturn target;}}}
package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface VarargsAnnot {}
class ParentClass {protected void log(String message) {System.out.println("Log: " + message);}
protected void validate(Object obj) {if (obj == null) {throw new NullPointerException("Object cannot be null");}}}
strictfp class TargetClass extends ParentClass {public String name;
public TargetClass(String name) {this.name = name;// Anonymous inner class in targetnew Runnable() {@Overridepublic void run() {System.out.println("Target initialized: " + name);}}.run();}}
protected class SourceClass extends ParentClass {class MemberInner {void processField(TargetClass target) {// EmptyStatement with obj.fieldif (target.name != null) ;else throw new NullPointerException("Name is null");}}
@VarargsAnnotTargetClass process(TargetClass... targets) {if (targets == null || targets.length == 0) {throw new NullPointerException("Targets array cannot be null or empty");}
// Type declaration statementTargetClass firstTarget = targets[0];Object varCall = firstTarget.name;
// Expression statementMemberInner inner = new MemberInner();
// Local inner classclass TargetHandler {void handle(TargetClass target) {inner.processField(target);super.log(target.name); // Call parent class method}}
// Array initialization with parent class instance methodsTargetHandler[] handlers = {new TargetHandler(),new TargetHandler()};handlers[0].handle(firstTarget);handlers[1].handle(targets.length > 1 ? targets[1] : firstTarget);
// Synchronized statementsynchronized (firstTarget) {firstTarget.name = firstTarget.name + "_processed";}
return firstTarget;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3137 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass target1 = new TargetClass("one");TargetClass target2 = new TargetClass("two");
TargetClass result = source.process(target1, target2);assertEquals("one_processed", result.name);}}
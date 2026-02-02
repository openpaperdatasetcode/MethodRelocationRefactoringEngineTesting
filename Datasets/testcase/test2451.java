package test.refactoring.movemethod;
import other.DiffPackageUtil;import java.util.ArrayList;import java.util.List;
class TargetParent {protected String superField = "parent_value";}
protected class TargetClass extends TargetParent {public TargetClass() {// Anonymous inner classnew Runnable() {@Overridepublic void run() {System.out.println("Target initialized");}}.run();}
protected void baseMethod() {System.out.println("Base method");}
protected void baseMethod(String arg) {System.out.println("Overloaded base method: " + arg);}}
class OtherClass {protected void callSuperMethod(TargetClass target) {target.baseMethod(); // Call super methodtarget.baseMethod("overload"); // Call overloaded super method}}
class SourceClass {static class SourceStaticNested {}
class SourceInner {public Object process(TargetClass target) {// Super constructor invocationclass Derived extends TargetParent {Derived() {super();}}new Derived();
// Variable callObject varCall = target.superField;
// With boundsList<? extends TargetParent> targetList = new ArrayList<>();targetList.add(target);
// Exception handling with others class overloading methodtry {new OtherClass().callSuperMethod(target);} catch (Exception e) {// No new exception}
// IfStatement with super.field in different packageDiffPackageUtil.checkSuperField(target);
return varCall;}}}
package other;
import test.refactoring.movemethod.TargetClass;
public class DiffPackageUtil {public static void checkSuperField(TargetClass target) {// IfStatement with super.fieldif (target.superField == null) {throw new IllegalArgumentException("Super field is null");}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3143 {@Testpublic void testInstanceMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass();
Object result = inner.process(target);assertEquals("parent_value", result);}}
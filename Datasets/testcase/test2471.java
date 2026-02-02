package test.refactoring.movemethod;
protected class TargetClass {public static int staticField = 5;public String instanceField;
public TargetClass(String instanceField) {this.instanceField = instanceField;// Local inner class in targetclass FieldInitializer {void init() {if (instanceField == null) {instanceField = "default";}}}new FieldInitializer().init();}
protected void normalMethod(int value) {instanceField += "_" + value;}}
public class SourceClass {// Member inner classclass SourceInner {}
// Anonymous inner classRunnable processor = new Runnable() {@Overridepublic void run() {System.out.println("Processing targets");}};
public TargetClass process(TargetClass... targets) {if (targets == null || targets.length == 0) {return new TargetClass("empty");}
// Super constructor invocationclass DerivedTarget extends TargetClass {DerivedTarget() {super("derived");}}DerivedTarget derived = new DerivedTarget();
// Variable callObject varCall = targets[0].instanceField;
// Access instance fieldString firstField = targets[0].instanceField;
// Expression statementTargetClass result = targets[0];result.instanceField = firstField + "_processed";
// ForStatement with ClassName.field (static field)private void processStaticField() {for (int i = 0; i < TargetClass.staticField; i++) {result.normalMethod(i);}}processStaticField();
// If/else conditions with target normal methodif (targets.length > 1) {TargetClass.methodName(targets[1]); // Call target class method} else {TargetClass.methodName(result);}
return result;}
// Static method to call target's protected methodprivate static void methodName(TargetClass target) {target.normalMethod(99);}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3168 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();TargetClass target1 = new TargetClass("test1");TargetClass target2 = new TargetClass("test2");
TargetClass result = source.process(target1, target2);assertEquals("test1_processed_0_1_2_3_4_99", result.instanceField);}}
package test.refactor.movemethod;
public strictfp class SourceClass {public void sourceUsage() {// Local inner classclass LocalInnerClass {public void useTargetMethod(TargetClass target) {new SourceClass().targetParameterMethod(target);}}
// Anonymous inner classRunnable runnable = new Runnable() {@Overridepublic void run() {new LocalInnerClass().useTargetMethod(new TargetClass());}};runnable.run();}
private void targetParameterMethod(TargetClass targetParam) {try {if (targetParam == null) {throw new IllegalArgumentException("Target parameter cannot be null");}// Variable calltargetParam.execute();} catch (IllegalArgumentException e) {// No new exception thrownSystem.err.println(e.getMessage());}}}
public class TargetClass {// Anonymous inner class in targetpublic void execute() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};runnable.run();}}
import org.junit.Test;import static org.junit.Assert.*;import java.lang.reflect.Method;
public class MoveMethodRefactoringTest5335 {@Testpublic void testMoveMethodRefactoring() throws Exception {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
// Direct usage testsource.sourceUsage();
// Reflection call (used_by_reflection feature)Method targetMethod = SourceClass.class.getDeclaredMethod("targetParameterMethod", TargetClass.class);targetMethod.setAccessible(true);targetMethod.invoke(source, target);
// Test null parameter casetry {targetMethod.invoke(source, (TargetClass) null);} catch (Exception e) {assertTrue(e.getCause() instanceof IllegalArgumentException);}}}
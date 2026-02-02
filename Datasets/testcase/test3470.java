package test;
import otherpackage.DiffPackageValidator;
// Public source record with two anonymous inner classespublic record SourceRecord(String data) {class InnerClass {class InnerRec {/**
Method javadoc: Processes target record and returns base type result
@param target TargetRecord instance
@return Processed integer result*/protected int process(TargetRecord target) {// First anonymous inner class (source_class feature)Runnable task1 = new Runnable() {@Overridepublic void run() {variableCall(target);}};
// Second anonymous inner class (source_class feature)Runnable task2 = new Runnable() {@Overridepublic void run() {target.processInner();}};
// ThrowStatement position (diff_package_others)DiffPackageValidator.validateObjField(target);
// Synchronized statementsynchronized (this) {// this.methodName(arguments)this.validateTarget(target);task1.run();task2.run();}
// Call_method: inner_class (exception throwing statements position)InnerProcessor processor = new InnerProcessor();try {processor.execute(target::processInner);} catch (Exception e) {throw new RuntimeException("Execution failed", e);}
return target.value() + data.length();}
private void validateTarget(TargetRecord target) {if (target.value() < 2) throw new IllegalArgumentException("Invalid value");}
private void variableCall(TargetRecord target) {target.doTask();}}}
// Call_method: inner_class, public modifier (abstract feature)public abstract class InnerProcessor {public abstract void execute(Runnable action) throws Exception;}
// Concrete implementation of InnerProcessorpublic class ConcreteProcessor extends InnerProcessor {@Overridepublic void execute(Runnable action) throws Exception {action.run();}}
// Trigger method to invoke inner refactoring methodpublic int triggerProcess(TargetRecord target) {return new InnerClass().new InnerRec().process(target);}}
// Public target record with anonymous inner class (target_feature)public record TargetRecord(int value) {public void doTask() {}
public void processInner() {// Anonymous inner class (target_feature)Runnable task = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous task executed");}};task.run();}}
// Different package class for ThrowStatement positionpackage otherpackage;
import test.TargetRecord;
public class DiffPackageValidator {public static void validateObjField(TargetRecord target) {// Private ThrowStatement (target_feature: obj.field=2)private int field = target.value();if (field != 2) throw new IllegalArgumentException("obj.field must be 2");}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetRecord target = new TargetRecord(2);SourceRecord source = new SourceRecord("test");int result = source.triggerProcess(target);assert result == 2 + 4 : "Refactoring test failed";System.out.println("Refactoring test passed");}}
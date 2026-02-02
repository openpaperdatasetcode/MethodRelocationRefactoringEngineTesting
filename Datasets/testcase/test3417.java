package test;
import java.lang.reflect.Method;
// Public source record with anonymous inner and local inner classespublic record SourceRecord(String data) {// Strictfp normal method (position: source)public strictfp TargetRecord process(TargetRecord target) {// Type declaration statementclass LocalType {}new LocalType();
// Super constructor invocation (in anonymous inner class)Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task: " + data);}};anonymousTask.run();
// Private SuperConstructorInvocation (target_feature: obj.field=2)private class Nested extends TargetRecord {Nested() {super(target.value() + 1); // SuperConstructorInvocationif (this.value() != 2) throw new IllegalArgumentException("obj.field must be 2");}}new Nested();
// Assert statementassert target.value() == 2 : "Target value mismatch";
// Throw statementif (target == null) throw new NullPointerException("Target cannot be null");
// Used by reflectiontry {Method method = TargetRecord.class.getDeclaredMethod("processLocal");method.invoke(target);} catch (Exception e) {throw new RuntimeException("Reflection failed", e);}
variableCall(target);return new TargetRecord(target.value() * 2);}
private void variableCall(TargetRecord target) {target.processLocal();}}
// Default target record with implements and local inner class (target_feature)record TargetRecord(int value) implements Processable {@Overridepublic void processLocal() {// Local inner class (target_feature)class TargetLocalInner {void validate() {if (value != 2) throw new IllegalArgumentException();}}new TargetLocalInner().validate();}}
// Interface for target record to implementinterface Processable {void processLocal();}
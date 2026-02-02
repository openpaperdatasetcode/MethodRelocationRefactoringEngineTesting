package test;
import java.util.List;
/**
Javadoc for SourceRecord
Public record class with member inner class and anonymous inner class*/public record SourceRecord(TargetRecord targetField) { // Contains target field (meets per_condition)// Member inner class (source_class feature)public class SourceMemberInner {// Generic method with bounds (with_bounds feature)public <T extends CharSequence> T processBoundedType(T input) {return input;}}
/**
Javadoc for overloaded methods (method type: overloading)
Processes TargetRecord and returns Object, no new exception thrown*/private Object overloadedMethod(TargetRecord target) {// Variable callvariableCall(target);
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {SourceMemberInner inner = new SourceMemberInner();inner.processBoundedType(target.value());}};runnable.run();
// Throw statement (conditional to avoid runtime error)if (target.value() == null) {throw new IllegalArgumentException("Target value cannot be null");}
// Access target's member inner classTargetRecord.TargetMemberInner targetInner = target.new TargetMemberInner();return targetInner.process(); // No new exception}
// Overloaded method (overloading feature)private Object overloadedMethod(TargetRecord target, List<String> extras) {variableCall(target);extras.add(target.value());return extras; // No new exception}
private void variableCall(TargetRecord target) {TargetRecord localTarget = target;localTarget.new TargetMemberInner().process();}}
// Target: default record class with member inner class (target_feature)record TargetRecord(String value) {// Member inner class (target_class target_feature)public class TargetMemberInner {public Object process() {return TargetRecord.this.value();}}}
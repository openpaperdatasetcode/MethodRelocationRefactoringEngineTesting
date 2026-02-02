package test;
import java.util.List;import java.util.ArrayList;
public record SourceRecord(TargetRecord targetField) {// Member inner class (source_class feature)public class MemberInner {}
// Protected varargs method (matches method requirements)protected Object varargsMethod(TargetRecord... targets) { // Contains target parameter (meets per_condition)// Constructor invocationTargetRecord newTarget = new TargetRecord("new");List<Object> results = new ArrayList<>();
// Try statementtry {// Enhanced for statementfor (TargetRecord target : targets) {// Variable callvariableCall(target);// Access instance methodresults.add(target.createLocalInner().process());// Overload exist: call overloaded methodsoverloadMethod(target);overloadMethod(target, "extra");}
// While statement (pos for call_method)int i = 0;while (i < targets.length) {TargetRecord current = targets[i];// instanceReference.methodName(arguments)callSourceMethod(current);i++;}} catch (Exception e) {// No new exception thrown}
// Anonymous inner class (source_class feature)Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField.value());}};runnable.run();
return results;}
// Overloaded methods (overload_exist feature)private void overloadMethod(TargetRecord target) {}private void overloadMethod(TargetRecord target, String extra) {}
// Private normal method (call_method: source type)private void callSourceMethod(TargetRecord target) {System.out.println("Called with: " + target.value());}
private void variableCall(TargetRecord target) {TargetRecord localTarget = target;localTarget.createLocalInner().process();}}
public record TargetRecord(String value) {// Local inner class (target_class feature)TargetRecord createLocalInner() {class LocalInner {Object process() {return TargetRecord.this;}}return new LocalInner().process();}}
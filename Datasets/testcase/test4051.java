package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
sealed record SourceClass(AbstractTargetClass<String> targetField) permits SourceSubClass {// Recursive method to be refactoredstrictfp List<String> recursiveProcess(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
// Expression statement + Variable callString targetVal = targetField.value();result.add(targetVal);
// Depends on target's local inner classAbstractTargetClass.StringProcessor processor = targetField.createProcessor();result.add(processor.process(targetVal));
// Used by reflectiontry {Method innerMethod = AbstractTargetClass.StringProcessor.class.getMethod("process", String.class);result.add((String) innerMethod.invoke(processor, targetVal + "_reflected"));} catch (Exception e) {// Exception handling statements (pos for inner instance methods)result.add(processor.handleError(e));result.add(processor.logError(e.getMessage()));result.add(processor.getDefaultValue());}
// Recursionresult.addAll(recursiveProcess(depth - 1));return result;}}
// Subclass for call_method (sub_class type)final class SourceSubClass extends SourceClass {protected SourceSubClass(AbstractTargetClass<String> targetField) {super(targetField);}
// Overriding call_method@Overrideprotected int recursiveProcess(int depth) {// Array initialization (pos for call_method)String[] arr = {super.recursiveProcess(depth).toString()};// this.methodName(arguments)return this.countElements(arr);}
private int countElements(String[] arr) {return arr.length;}}
abstract record AbstractTargetClass<T>(T value) {// Type parameter (target_class feature)// Local inner class (target_class feature)public StringProcessor createProcessor() {class LocalStringProcessor implements StringProcessor {@Overridepublic String process(T input) {return input.toString().toUpperCase();}
@Overridepublic String handleError(Exception e) {return "Error: " + e.getClass().getSimpleName();}
@Overridepublic String logError(String msg) {return "Log: " + msg;}
@Overridepublic String getDefaultValue() {return "Default";}}return new LocalStringProcessor();}
// Inner interface for inner_class instance methodspublic interface StringProcessor {// 1st inner_class instance methodString process(T input);// 2nd inner_class instance methodString handleError(Exception e);// 3rd inner_class instance methodString logError(String msg);// Additional method for super.methodName()String getDefaultValue();}}
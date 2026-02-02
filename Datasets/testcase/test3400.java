package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
// Source interface (empty modifier) extending parent interfaceinterface SourceInterface extends ParentInterface {/**
Method javadoc: Retrieves and processes data from TargetInterface
@param helper TargetInterface's static nested class instance
@return Processed string list
@throws IOException If processing fails
*/
public List<String> processData(TargetInterface.StaticHelper helper) throws IOException;
// Default method implementing core logicdefault List<String> process(TargetInterface.StaticHelper helper) throws IOException {List<String> result = new ArrayList<>();// Access outer protected field from parent interfaceresult.add(ParentInterface.protectedField);
// Private ForStatement (target_feature: ClassName.field=1)private int count = 0;for (; count < 3; count++) {if (TargetInterface.StaticHelper.staticField != 1) break; // Break statement
// TypeMethodReference (numbers=3, default modifier)result.add(String.valueOf(String::valueOf.apply(count)));result.add(String.valueOf(Integer::toString.apply(count)));result.add(String.valueOf(List::size.apply(result)));
variableCall(helper);if (count == 2) break;}
// Override violation (Object.equals() return type mismatch)@Overridedefault boolean equals(Object obj) {return result.equals(obj);}
callMethod(helper);return result;}
private void variableCall(TargetInterface.StaticHelper helper) {helper.processStep();}
// Call_method: others_class, public modifierdefault int callMethod(TargetInterface.StaticHelper helper) {// Instance code blocks positionint value = OthersClass.process(helper, "param");return value;}}
// Parent interface with protected fieldinterface ParentInterface {protected String protectedField = "parent_protected";}
// Protected target interface with static nested classprotected interface TargetInterface {static class StaticHelper {public static int staticField = 1; // ClassName.field=1public void processStep() {}}
// Inherited method from parent interfaceint calculate(int a, int b);}
// Others class for call_methodclass OthersClass {// Normal method with super.methodName(arguments)public static int process(TargetInterface.StaticHelper helper, String param) {ParentProcessor processor = new ParentProcessor();return processor.superProcess(helper, param); // Super method invocation}}
// Parent class for super method invocationclass ParentProcessor {protected int superProcess(TargetInterface.StaticHelper helper, String param) {return param.length() + helper.staticField;}}
// Implementation class to satisfy interface method requirementsclass SourceImplementation implements SourceInterface {@Overridepublic List<String> processData(TargetInterface.StaticHelper helper) throws IOException {return process(helper);}
@Overridepublic int calculate(int a, int b) {return a + b;}}
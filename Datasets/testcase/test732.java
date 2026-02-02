import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;
strictfp interface TestInterface {Object method(String... args);}
strictfp class TargetClass<T> {private String outerPrivateField = "targetPrivate";
class TargetInnerClass {String innerField = "targetInner";
String getOuterPrivate() {return outerPrivateField;}}
public TargetClass() {}}
strictfp class SourceClass implements TestInterface {private String sourcePrivateField = "sourcePrivate";TargetClass<String> targetField = new TargetClass<>();
class SourceInnerClass1 {String inner1Field = "inner1";
String accessOuterPrivate() {return sourcePrivateField;}}
class SourceInnerClass2 {String inner2Field = "inner2";}
/**
Javadoc for the varargs method to be moved
@param args variable arguments
@return Object instance*/@OverrideObject method(String... args) {// Type declaration statementclass LocalTypeDeclaration {String localField = targetField.new TargetInnerClass().innerField;}
// Expression statementLocalTypeDeclaration localInstance = new LocalTypeDeclaration();String variableCallResult = localInstance.localField;
// Access outer private fieldString outerPrivate = new SourceInnerClass1().accessOuterPrivate();
// Variable callvariableCall(variableCallResult, outerPrivate);
// Depends on inner classSourceInnerClass2 inner2 = new SourceInnerClass2();String inner2Value = inner2.inner2Field;
// Collection operations with call_method (constructor + method reference)List<TargetClass<String>> targetList = callMethod(args);
return new Object();}
private void variableCall(String... vars) {for (String var : vars) {System.out.println(var);}}
public TargetClass<String> callMethod(String... args) {List<String> argList = new ArrayList<>();for (String arg : args) {argList.add(arg);}
// Collection operations with constructor and method referenceList<TargetClass<String>> result = argList.stream().map(TargetClass::new).collect(Collectors.toList());
return result.isEmpty() ? new TargetClass<>() : result.get(0);}}

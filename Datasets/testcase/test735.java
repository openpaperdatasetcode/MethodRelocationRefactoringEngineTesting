import java.util.List;
// Target class (public, member inner class)public class TargetClass {String instanceField = "targetInstanceField";
// Member inner class (target_inner)class TargetInnerClass {int innerField = 3;
Object varargsMethod(String... args) {return args.length;}}
public TargetClass() {}}
// Others class for varargs method featureclass OthersClass {Object callInnerMethod(TargetClass.TargetInnerClass inner) {return new TargetClass().new TargetInnerClass().varargsMethod("arg1", "arg2");}}
// Source class (protected, type parameter, static nested class, local inner class)protected class SourceClass<T extends List<String>> {// Field of target class (per_condition)TargetClass targetField = new TargetClass();
// Static nested classstatic class SourceStaticNestedClass {String nestedField = "staticNested";}
// Method to be refactored (instance, TargetClass return, default access, source position)TargetClass method() {// NullPointerException possibilityString nullable = null;if (nullable != null) {nullable.length();}
// Constructor invocationTargetClass newTarget = new TargetClass();TargetClass.TargetInnerClass inner = newTarget.new TargetInnerClass();
// Switch caseint switchVar = inner.innerField;switch (switchVar) {case 3:break;default:break;}
// Type declaration statementclass LocalTypeDeclaration {int localField = switchVar;}
// Array initialization with varargs method (pos: array initialization)Object[] arr = {varargsMethod("arg1", "arg2", "arg3")};
// This method call with argumentsthis.variableCall(inner.innerField);
// Variable callSourceStaticNestedClass staticNested = new SourceStaticNestedClass();String varCall = staticNested.nestedField;
// With bounds (type parameter T extends List<String>)T boundedType = null;if (boundedType != null) {boundedType.add(varCall);}
// Access instance field (target class instance field)String accessedField = targetField.instanceField;
// Local inner classclass LocalInnerClass {String localInnerField = accessedField;}
// No new exception (no throw/new Exception)return newTarget;}
// Varargs method (protected, 3, others_class, varargs, new ClassName().method())protected Object varargsMethod(String... args) {OthersClass others = new OthersClass();return others.callInnerMethod(targetField.new TargetInnerClass());}
// Variable call helper methodprivate void variableCall(int arg) {System.out.println(arg);}}
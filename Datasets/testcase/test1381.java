package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.util.ArrayList;import java.util.List;
protected class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass<String> targetField = new TargetClass<>();
// Source feature: local inner classpublic void sourceWithLocalInner() {class LocalInnerClass {void localMethod() {}}new LocalInnerClass().localMethod();}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {}};anonymous.run();}
// Method to be refactored: varargs, public, return List<String>public List<String> refactorVarargsMethod(String... args) {// NullPointerException (explicit check)if (args == null) throw new NullPointerException("args cannot be null");
// Constructor invocation (target class constructor)TargetClass<String> newTarget = new TargetClass<>("constructorArg");
// Type declaration statementclass MethodLocalType {}MethodLocalType localTypeInstance = new MethodLocalType();
// Variable callTargetClass<String> tempTarget = targetField;List<String> targetData = tempTarget.getData();
// Switch caseString switchParam = args.length > 0 ? args[0] : "";switch (switchParam) {case "test":targetData.add("caseTest");break;default:targetData.add("defaultCase");}
// No new exception (no additional checked/unchecked exceptions thrown)return new ArrayList<>(targetData);}
// Call method: parent_class type, public, pos in ternary operators, return List<String>public List<String> callParentMethod(boolean flag) {// Ternary operator position + super.methodName(arguments) + instance featurereturn flag ? super.toString() : super.getClass().getName();}}
// Different package: test.refactoring.targetpackage test.refactoring.target;
import java.util.List;import java.util.ArrayList;
// Target class: normal class, public modifier, has type parameter (target_feature)public class TargetClass<T> {private List<String> data = new ArrayList<>();private T typeParamField;
// Constructor for invocation in source methodpublic TargetClass() {}
public TargetClass(T typeParamField) {this.typeParamField = typeParamField;}
// Getter for variable call in source methodpublic List<String> getData() {return data;}}
// Parent class for call_method (parent_class type)package test.refactoring.source;
import java.util.List;import java.util.ArrayList;
public class ParentClass {// Super method for super.methodName(arguments) in call_methodpublic List<String> getParentData(String arg) {List<String> parentList = new ArrayList<>();parentList.add(arg);return parentList;}}
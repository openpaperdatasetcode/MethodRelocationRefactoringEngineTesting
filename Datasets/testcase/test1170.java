package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
/**
Source class with static nested class and anonymous inner class features*/public class SourceClass {// Per_condition: source contains the field of the targetprivate TargetClass targetField = new TargetClass(100);
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};anonymous.run();}
// Source inner class (method_position: source_inner)class SourceInnerClass {/**
Instance method to be refactored (private access, returns TargetClass type)
@return TargetClass instance
@throws IOException required checked exception (requires_throws)*/@RefactorRequired // has_annotation featureprivate TargetClass refactorTargetMethod() throws IOException {// Empty statement;
// Type declaration statementclass MethodLocalType {}MethodLocalType localInstance = new MethodLocalType();
// Expression statementtargetField.toString();
// Variable callTargetClass tempTarget = SourceClass.this.targetField; // uses_outer_this
// Access instance field (target class instance field)int targetFieldValue = tempTarget.instanceField;
// Synchronized statementsynchronized (tempTarget) {tempTarget.staticNestedClass.method();}
// Assert statementassert targetFieldValue > 0 : "Target field value must be positive";
// Uses outer_this (explicit reference to SourceClass instance)SourceClass outerThis = SourceClass.this;outerThis.sourceWithAnonymousInner();
// Simulate requires_throws: throw checked exception if invalidif (tempTarget == null) {throw new IOException("Target instance cannot be null");}
return tempTarget;}}
// Custom annotation for has_annotation feature@interface RefactorRequired {}
// Call method: source type, private modifier, pos in array initializationprivate List<String> callSourceVarargsMethod(String... baseArgs) {// Array initialization position + varargs + ClassName.methodName(arguments)String[] extendedArgs = {baseArgs[0], "extra_arg"};return SourceStaticHelper.processVarargs(extendedArgs); // ClassName.methodName(arguments)}
// Static helper class for call_methodprivate static class SourceStaticHelper {// Varargs method for call_method target_featurepublic static List<String> processVarargs(String... args) {List<String> result = new ArrayList<>();for (String arg : args) {result.add(arg);}return result;}}}
/**
Target class: strictfp modifier, target_feature: static nested class*/strictfp class TargetClass {// Target instance field for access_instance_field featurepublic int instanceField;
// Target feature: static nested class (target_inner_rec)public static class TargetStaticNestedClass {public void method() {}}
// Static nested class instance (for variable call)public static final TargetStaticNestedClass staticNestedClass = new TargetStaticNestedClass();
// Constructorpublic TargetClass(int instanceField) {this.instanceField = instanceField;}}
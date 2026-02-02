package test;
abstract class SourceClass extends ParentClass {// Member inner class (source_class feature)class SourceMemberInner {}
// Anonymous inner class (source_class feature)Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceMemberInner();}};
// Overriding method to be refactored (matches method structure)@Overridevoid processTarget(TargetClass targetParam) {// 3 VariableDeclarationStatements with "ClassName.field" (matches nested feature)private int field1 = TargetClass.StaticNestedClass.STATIC_FIELD1;private int field2 = TargetClass.StaticNestedClass.STATIC_FIELD2;private int field3 = TargetClass.StaticNestedClass.STATIC_FIELD3;
// Constructor invocationSourceMemberInner innerInst = new SourceMemberInner();// Variable callint sum = field1 + field2 + field3 + targetParam.targetInstanceField;
// Return statement (void method allows empty return)return;}}
// Parent class for overriding and super constructorabstract class ParentClass {// Super constructor (for "super constructor invocation")public ParentClass() {}
// Abstract method to be overriddenabstract void processTarget(TargetClass targetParam);}
// Target class (matches target_class structure)abstract class TargetClass {// Static nested class (target_class target_feature)static class StaticNestedClass {public static final int STATIC_FIELD1 = 10;public static final int STATIC_FIELD2 = 20;public static final int STATIC_FIELD3 = 30;}
// Instance field (for variable call)int targetInstanceField = 40;}
package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;
/**
Private record source class with static nested and member inner class features*/private record SourceRecord() {// Per_condition: source contains the field of the targetprivate TargetRecord targetField = new TargetRecord(100);
// Source feature: static nested classpublic static class SourceStaticNestedClass {}
// Source feature: member inner classclass SourceMemberInnerClass {// Generic nested method (type: generic, modifier: default, return_type: TargetClass Type)default <T> TargetRecord genericNestedMethod(T arg) {int num = 1; // "1" in method_featureTargetRecord result = SourceRecord.this.refactorTargetMethod(); // "this.methodName(arguments)"return result; // "inner_class" + "generic" features}}
/**
Instance method to be refactored (strictfp, returns TargetRecord type)
@return TargetRecord instance*/@RefactorAnnotation // has_annotation featurestrictfp TargetRecord refactorTargetMethod() {// Labeled statementouterLoop:for (int i = 0; i < 3; i++) {for (int j = 0; j < 2; j++) {if (j == 1) break outerLoop; // Break statement}}
// Variable callTargetRecord tempTarget = targetField;tempTarget.invokeLocalInner();
// Raw type usage (raw_type feature)List rawList = new ArrayList();rawList.add(tempTarget.value());
// Exception handling statements (pos for generic nested method)try {SourceMemberInnerClass inner = new SourceMemberInnerClass();TargetRecord nestedResult = inner.genericNestedMethod("arg");} catch (Exception e) {// No new exception thrown}
return tempTarget;}
// Custom annotation for has_annotation feature@interface RefactorAnnotation {}
// Instance initializer block (instance code blocks){// Call method: target type, public modifier, pos in instance code blockspublic int callTargetInstanceMethod() {// Instance feature + this.methodName(arguments)return targetField.targetInstanceMethod(5);}}}
/**
Protected target record class with local inner class (target_feature)*/protected record TargetRecord(int value) {// Target feature: local inner classpublic void invokeLocalInner() {class TargetLocalInnerClass {void innerMethod() {System.out.println("Target local inner class: " + value);}}TargetLocalInnerClass localInner = new TargetLocalInnerClass();localInner.innerMethod();}
// Target instance method for call_methodpublic int targetInstanceMethod(int arg) {return value + arg;}}
// Container class to access private record (since private records are package-private in practice)class SourceRecordContainer {public SourceRecord createSourceRecord() {return new SourceRecord();}}
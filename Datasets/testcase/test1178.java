package test.refactoring.movemethod;
import java.io.IOException;
/**
Source class with required features*/public class SourceClass {// Source feature: member inner classclass SourceMemberInnerClass {}
// Source feature: anonymous inner classpublic void sourceWithAnonymousInner() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class execution");}};anonymous.run();}
/**
Varargs method to be refactored (default access, returns TargetClass type)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return TargetClass instance
@throws IOException required checked exception (requires_throws)*/@RefactorAnnotation // has_annotation featureTargetClass refactorTargetMethod(TargetClass targetParam, String... varargs) throws IOException {// Variable callTargetClass tempTarget = targetParam;
// Depends on static field (target class static field)if (TargetClass.STATIC_FIELD > 0) {tempTarget.new TargetMemberInnerClass().innerMethod();}
// Simulate required_throws condition (throw checked exception if necessary)if (varargs == null) {throw new IOException("Varargs cannot be null");}
return tempTarget;}
// Custom annotation for has_annotation feature@interface RefactorAnnotation {}}
/**
Target class: strictfp modifier, contains member inner class (target_feature)*/strictfp class TargetClass {// Static field for depends_on_static_field featurepublic static final int STATIC_FIELD = 100;
// Target feature: member inner classpublic class TargetMemberInnerClass {public void innerMethod() {}}}
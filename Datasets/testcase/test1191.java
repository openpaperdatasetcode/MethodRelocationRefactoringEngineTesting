package test.refactoring.source;
import test.refactoring.target.TargetClass;import java.io.IOException;
/**
Abstract source class: different package with target, implements interface, has two member inner classes*/public abstract class SourceClass implements SourceInterface {// Source feature: first member inner classpublic class SourceMemberInner1 {public void method1() {}}
// Source feature: second member inner classpublic class SourceMemberInner2 {public void method2() {}}
/**
Varargs method to be refactored (default access, returns TargetClass type)
@param targetParam target class parameter (per_condition)
@param varargs varargs parameters
@return TargetClass instance*/TargetClass refactorTargetMethod(TargetClass targetParam, String... varargs) {// Constructor invocation (target class constructor)TargetClass newTarget = new TargetClass();
// Variable callTargetClass tempTarget = targetParam;
// Try statement (no exception thrown, meets no_new_exception)try {// Switch statementswitch (varargs.length) {case 0:tempTarget.doOperation("default");break;case 1:tempTarget.doOperation(varargs[0]);break;default:tempTarget.doOperation(varargs[varargs.length - 1]);break;}} catch (RuntimeException e) {// No new exception thrown (swallow or handle without propagating)}
// No new exception thrownreturn tempTarget;}
@Overridepublic void interfaceMethod() {}}
// Interface for source class implements featureinterface SourceInterface {void interfaceMethod();}
// Different package: test.refactoring.targetpackage test.refactoring.target;
/**
Target class: default modifier, no additional target features*/class TargetClass {// Constructor for constructor invocation featurepublic TargetClass() {}
public void doOperation(String arg) {// Dummy operation for variable call}}
// Concrete subclass of abstract source class to enable method usagepackage test.refactoring.source;
public class ConcreteSourceClass extends SourceClass {// Provide concrete implementation if needed (abstract class requires no abstract methods here)}
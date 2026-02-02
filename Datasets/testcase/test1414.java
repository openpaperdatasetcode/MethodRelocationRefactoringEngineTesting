package test.refactoring.movemethod;
import java.util.List;
private class SourceClass {// Static nested class (feature: static nested class x2)public static class FirstStaticNested {// Static nested class (second static nested class)public static class SecondStaticNested {/**
Method javadoc (feature: method javadoc)
Varargs method to be refactored (type: varargs)
@param targetParam TargetClass parameter (per_condition: contains target parameter)
@param args Varargs parameters*/protected <T extends CharSequence & Comparable<T>> void varargsSourceMethod(TargetClass targetParam, T... args) {// With_bounds (type parameter bounds: CharSequence & Comparable<T>)if (args == null || args.length == 0) return;
// Switch statement (feature: switch statement)switch (args[0].length()) {case 1:System.out.println("Length 1");break;case 2:System.out.println("Length 2");break;default:System.out.println("Other length");}
// Variable call (feature: variable call)String var = targetParam.getName();var.toUpperCase();targetParam.printArgs(args);}}}
public void invokeInnerMethod() {TargetClass target = new TargetClass();FirstStaticNested.SecondStaticNested inner = new FirstStaticNested.SecondStaticNested();inner.varargsSourceMethod(target, "a", "bc", "def");}}
public class TargetClass {private String name = "TargetClass";
public String getName() {return name;}
public <T extends CharSequence> void printArgs(T... args) {for (T arg : args) {System.out.println(arg);}}
// Target inner recursive structure (target_inner_rec)public static class TargetInner {public static class TargetInnerRec {// Refactored method will be moved hereprotected <T extends CharSequence & Comparable<T>> void varargsSourceMethod(TargetClass targetParam, T... args) {if (args == null || args.length == 0) return;
switch (args[0].length()) {case 1:System.out.println("Length 1");break;case 2:System.out.println("Length 2");break;default:System.out.println("Other length");}
String var = targetParam.getName();var.toUpperCase();targetParam.printArgs(args);}}}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest5042 {@Testpublic void testMoveVarargsMethodFromSourceInnerToTargetInnerRec() {// Test original source method invocationSourceClass source = new SourceClass();source.invokeInnerMethod();
// Test refactored method in target inner recursive structureTargetClass target = new TargetClass();TargetClass.TargetInner.TargetInnerRec targetInnerRec = new TargetClass.TargetInner.TargetInnerRec();
// Verify method execution with target parameter (per_condition compliance)targetInnerRec.varargsSourceMethod(target, "x", "yz");assertNotNull(target.getName());assertEquals("TargetClass", target.getName());}}
package test.refactor.movemethod;
import java.io.IOException;
interface ParentInterface {void doAction();}
public class SourceClass implements ParentInterface {// Source contains target class field (per_condition)private AbstractTargetClass targetField = new AbstractTargetClass() {};
// Static nested class (source_class feature)public static class SourceInnerClass implements ParentInterface {/**
Javadoc for overriding method to be refactored
Overrides ParentInterface#doAction*/@Overridepublic void doAction() {// Type declaration statementClass<?> stringType = String.class;SourceClass outer = new SourceClass();
// Accessor feature (type: accessor, modifier: protected, pos: ternary operators)protected Object getTargetValue() {// superTypeReference.methodName(arguments)return outer.targetField.superInterfaceMethod(3) ?outer.targetField.getTargetField() : new Object();}
// Switch statementString type = "test";switch (type) {case "test":// Variable callObject value = getTargetValue();System.out.println(value);break;default:break;}
// Requires_try_catch (checked exception)try {outer.targetField.throwCheckedException();} catch (IOException e) {e.printStackTrace();}
// Anonymous inner class (source_class feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous inner class");}};anonymous.run();}}
@Overridepublic void doAction() {}}
abstract class AbstractTargetClass implements SuperInterface {// Target feature: anonymous inner classprotected Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class");}};
private String targetField = "targetFieldValue";
public String getTargetField() {return targetField;}
// Refactored overriding method should be placed here (target class: target)@Overridepublic void doAction() {Class<?> stringType = String.class;AbstractTargetClass target = this;
protected Object getTargetValue() {return target.superInterfaceMethod(3) ?target.getTargetField() : new Object();}
String type = "test";switch (type) {case "test":Object value = getTargetValue();System.out.println(value);break;default:break;}
try {target.throwCheckedException();} catch (IOException e) {e.printStackTrace();}
targetAnonymous.run();}}
interface SuperInterface {boolean superInterfaceMethod(int num);void throwCheckedException() throws IOException;}
// Test class for refactoring verificationpublic class MoveMethodTest5052 {public static void main(String[] args) {// Original method call (before refactoring)SourceClass.SourceInnerClass sourceInner = new SourceClass.SourceInnerClass();sourceInner.doAction();
// Refactored method call (after moving to AbstractTargetClass)AbstractTargetClass target = new AbstractTargetClass() {};target.doAction();}}
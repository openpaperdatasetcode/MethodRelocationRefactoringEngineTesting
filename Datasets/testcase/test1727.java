package test;
import java.util.ArrayList;import java.util.List;import java.util.stream.Collectors;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SourceClass {static class StaticNested {}
{new Runnable() {}; // Anonymous inner class}
@MyAnnotationstrictfp List<String> normalMethod(TargetClass.InnerClass targetInner) {// Access target fieldString fieldVal = targetInner.targetField;
// Super constructor invocationclass SubTarget extends TargetClass {SubTarget() {super();}}
// Instance method from target in exception handlingTargetClass targetInstance;try {targetInstance = targetInner.instanceMethod();} catch (Exception e) {targetInstance = new TargetClass();}
variableCall();
// Call others_class instance method via method reference in collection operationsList<Object> othersList = new ArrayList<>();List<Object> processed = othersList.stream().map(OthersClass::instanceMethod).collect(Collectors.toList());
return new ArrayList<>();}
private void variableCall() {}}
class TargetClass {class InnerClass {String targetField;
public TargetClass instanceMethod() {return new TargetClass();}}
{new Runnable() {}; // Anonymous inner class}}
class OthersClass {public static Object instanceMethod(Object obj) {return obj;}}
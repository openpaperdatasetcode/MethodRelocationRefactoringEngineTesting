package test;
interface ParentInterface {void processTarget(TargetClass target);}
class SourceClass implements ParentInterface {private String outerPrivate = "outerPrivateData";
// Member inner class (source_inner)public class SourceInner implements ParentInterface {@Overridepublic void processTarget(TargetClass target) {super(); // Super constuctor invocation// Variable call + contains target field (per_condition)target.toString();
// Access outer private fieldString combined = target.targetField + SourceClass.this.outerPrivate;
// Constructor invocationTargetClass.LocalHelper helper = new TargetClass.LocalHelper();
// Enhanced for statementString[] data = {combined, target.targetField, outerPrivate};for (String s : data) {if (s.length() > 15) {break; // Break statement}helper.append(s);}
// Requires try-catch (checked exception simulation)try {helper.validate();} catch (IllegalStateException e) {// Mandatory try-catch handling}
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Processed: " + helper.getResult());}};anon.run();}}
@Overridepublic void processTarget(TargetClass target) {new SourceInner().processTarget(target);}}
/**
TargetClass with javadoc (target_feature)
Handles data processing and validation*/private class TargetClass {public String targetField = "targetData"; // Source contains target's field (per_condition)
/**
LocalHelper for data manipulation
Contains local inner class dependency*/public class LocalHelper {private StringBuilder result = new StringBuilder();
public void append(String s) {result.append(s);}
public String getResult() {return result.toString();}
public void validate() {if (result.length() == 0) {throw new IllegalStateException("Empty result");}}}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public void print() {System.out.println(targetField);}}new TargetLocalInner().print();}}
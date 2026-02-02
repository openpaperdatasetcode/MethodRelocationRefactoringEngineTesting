package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
// Super type for superTypeReference.methodName(arguments)class SuperType {public static List<String> processSuperMethod(String arg) {List<String> result = new ArrayList<>();result.add("SuperProcessed:" + arg);return result;}}
// Interface for source_class implements featureinterface Processable {TargetClass process(TargetClass target);}
// Source class (protected, same package, implements + two anonymous inner classes)protected class SourceClass extends SuperType implements Processable {// Private field for access_outer_privateprivate String outerPrivateField = "outerPrivateValue";
// Super constructor invocation (implicit default constructor, explicit super call if needed)public SourceClass() {super();}
// Member inner class (source_inner_rec: recursive inner structure)public class SourceInner {public class RecursiveInner {// Method to be refactored: instance, public, TargetClass return, in source_inner_rec@Overridepublic TargetClass process(TargetClass targetParam) { // per_condition// Per_condition: source contains target's fieldString targetField = targetParam.targetField;
// Access_outer_privateString privateValue = SourceClass.this.outerPrivateField;
// If statementif (targetField == null) {throw new IllegalArgumentException("Target field cannot be null");}
// Variable call: target's anonymous inner classRunnable targetAnon = targetParam.createTargetAction();targetAnon.run();
// First anonymous inner class (source feature)Runnable sourceAnon1 = new Runnable() {@Overridepublic void run() {System.out.println("SourceAnon1: " + privateValue);}};
// Second anonymous inner class (source feature - duplicate)Runnable sourceAnon2 = new Runnable() {@Overridepublic void run() {System.out.println("SourceAnon2: " + targetField);}};
// Constructor feature (private, source, superTypeReference.methodName(arguments), pos: property assignment)PrivateConstructorHelper helper = new PrivateConstructorHelper();List<String> constructorResult = helper.process();targetParam.setProcessedData(constructorResult); // Property assignment
try {sourceAnon1.run();sourceAnon2.run();
// Variable call: target's instance methodtargetParam.updateField(privateValue + "_updated");} catch (Exception e) {// no_new_exception: rethrow without wrappingthrow e;}
return targetParam; // TargetClass Type return}}}
// Private constructor helper class (for constructor feature)private class PrivateConstructorHelper {// Private constructor (method_feature: constructor)private PrivateConstructorHelper() {}
public List<String> process() {// superTypeReference.methodName(arguments)return SuperType.processSuperMethod(SourceClass.this.outerPrivateField);}}
// Delegate to recursive inner class method@Overridepublic TargetClass process(TargetClass target) {SourceInner inner = new SourceInner();SourceInner.RecursiveInner recursive = inner.new RecursiveInner();return recursive.process(target);}}
// Target class (public, target_feature: anonymous inner class)public class TargetClass {public String targetField = "targetValue";private List<String> processedData;
// Target_feature: anonymous inner classpublic Runnable createTargetAction() {return new Runnable() {@Overridepublic void run() {System.out.println("TargetAnonAction: " + targetField);}};}
public void updateField(String value) {this.targetField = value;}
public void setProcessedData(List<String> processedData) {this.processedData = processedData;}
public List<String> getProcessedData() {return processedData;}}
// Test classpublic class MoveMethodTest5261 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();TargetClass result = source.process(target);System.out.println("Refactored Target Field: " + result.targetField);System.out.println("Processed Data: " + result.getProcessedData());}}
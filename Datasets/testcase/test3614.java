package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnot {}
interface TargetInterface {}
public class SourceClass {private String instanceField = "sourceFieldValue";
Object instanceMethod(FinalTargetClass target) {@RefactorAnnotclass LocalInner {String getLocalData() {return "localInnerData";}}LocalInner local = new LocalInner();
new Runnable() {public void run() {System.out.println(instanceField);}}.run();
FinalTargetClass.StaticNested targetNested = new FinalTargetClass.StaticNested();targetNested.process(this);
variableCall(targetNested);Object fieldValue = target.instanceField;
return local.getLocalData();}
private void variableCall(FinalTargetClass.StaticNested nested) {nested.printData();}}
final class FinalTargetClass implements TargetInterface {String instanceField = "targetFieldValue";
static class StaticNested {void process(SourceClass source) {System.out.println("Processing source instance: " + source);}
void printData() {System.out.println("Target static nested class data");}}}
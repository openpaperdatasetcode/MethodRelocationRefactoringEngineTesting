package test;
import java.io.IOException;import java.util.List;import java.util.ArrayList;
/**
Javadoc for TargetClass
Contains static nested class for refactoring dependency*/public class TargetClass {public String targetField;public static class TargetInnerRec {}
public void instanceMethod() {}}
private class SourceClass extends ParentClass {class SourceInner {@RefactorAnnotationstrictfp <T extends CharSequence> List<String> methodToMove(TargetClass.TargetInnerRec param) throws IOException {return methodToMove(param, new ArrayList<>());}
@RefactorAnnotationstrictfp <T extends CharSequence> List<String> methodToMove(TargetClass.TargetInnerRec param, List<T> data) throws IOException {labeled: {TargetClass target = new TargetClass();target.targetField = "test";; // Empty statement
TargetClass.TargetInnerRec innerRec = new TargetClass.TargetInnerRec();target.instanceMethod(); // Access instance methodsuper.parentMethod(); // Super keyword
String var = target.targetField; // Variable calldata.add((T) var); // Expression statement}
return new ArrayList<>(data);}}
public void triggerRefactor() throws IOException {TargetClass target = new TargetClass();TargetClass.TargetInnerRec innerRec = new TargetClass.TargetInnerRec();SourceInner sourceInner = new SourceInner();
do {TargetClass::instanceMethod;sourceInner.methodToMove(innerRec);} while (false);}}
class ParentClass {protected void parentMethod() {}}
@interface RefactorAnnotation {}
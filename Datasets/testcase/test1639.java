package test;
import other.OthersClass;import java.io.IOException;import java.util.ArrayList;import java.util.List;
class ParentClass {protected synchronized Object formatData(String data) {return data.trim();}}
public class SourceClass extends ParentClass {private String outerPrivate = "outer_private_data";
public class MemberInner {public class InnerRec {// Method in source_inner_rec with private access modifierprivate Object process(TargetClass.Inner.Rec targetRec) throws IOException {// Local inner class 1class RecValidator {boolean isValid(TargetClass.Inner.Rec rec) {return rec.value != null;}}RecValidator validator = new RecValidator();
// Local inner class 2class RecProcessor {Object handle(TargetClass.Inner.Rec rec) {return rec.value;}}RecProcessor processor = new RecProcessor();
// Constructor invocationTargetClass target = new TargetClass();TargetClass.Inner.Rec newRec = target.new Inner().new Rec("new_data");
// Super constructor invocation in subclassclass SubRec extends TargetClass.Inner.Rec {SubRec(String value) {super(value);}}SubRec subRec = new SubRec(outerPrivate);
// Empty statement;
// Private ContinueStatement with 2 obj.field accesses (diff_package_others)List<String> items = new ArrayList<>();for (int i = 0; i < 5; i++) {if (i % 2 == 0) {OthersClass.check(targetRec.value);if (targetRec.value == null) continue; // 1st obj.field accessOthersClass.check(subRec.value);if (subRec.value == null) continue; // 2nd obj.field access}items.add(targetRec.value + "_" + i);}
// Throw statementif (!validator.isValid(targetRec)) {throw new IllegalArgumentException("Invalid target record");}
// Variable call - access target inner rec's fieldObject result = targetRec.value;
// Access outer private fieldtargetRec.value += "_" + outerPrivate;
// Access instance fieldtargetRec.counter++;
// Overloaded methods existprocessOverload(targetRec);processOverload(targetRec, 100);
// Parent class's synchronized method reference in switchswitch (targetRec.counter) {case 1:Object formatted1 = SourceClass.this::formatData;break;case 2:Object formatted2 = SourceClass.this::formatData;break;default:break;}
// Requires throwsif (targetRec.value.length() > 50) {throw new IOException("Value too long");}
return processor.handle(newRec);}
// Overloaded methodsprivate void processOverload(TargetClass.Inner.Rec rec) {}private void processOverload(TargetClass.Inner.Rec rec, int factor) {}}}}
/**
TargetClass with Javadoc
Contains member inner class Inner which has Rec as inner class
/
class TargetClass {
public class Inner {
/*
Rec class to hold record data*/public class Rec {public String value;public int counter;
public Rec(String value) {this.value = value;this.counter = 0;}}}}
package other;
public class OthersClass {public static void check(String value) {}}
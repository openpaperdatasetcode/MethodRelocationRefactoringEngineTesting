package test;
import otherpackage.DiffPackageHelper;import java.util.ArrayList;import java.util.List;
// Protected abstract source class with two member inner classesprotected abstract class SourceClass {private String outerPrivateField = "private_data";
// Two member inner classes (source_class feature)class MemberInner1 {}class MemberInner2 {}
class InnerClass {class InnerRec {// Default access instance method (position: source_inner_rec)TargetClass process(TargetClass target) throws Exception {// NullPointerExceptionif (target == null) throw new NullPointerException("Target cannot be null");
// If statementif (target.field == 1) {// Access outer private fieldtarget.setData(outerPrivateField);}
// Assert statementassert target.field == 1 : "Field value mismatch";
// While loop (generic method_feature position)int count = 0;while (count < 1) {target = target.<String>genericMethod("param");count++;}
// Diff_package_target WhileStatement positionDiffPackageHelper.validateTargetField(target);
variableCall(target);callMethod(target);new MemberInner1();new MemberInner2();return target;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}
// Call_method: target, private modifierprivate List<String> callMethod(TargetClass target) {List<String> result = new ArrayList<>();// For loop positionfor (int i = 0; i < 1; i++) {// Method chain: obj.m1().m2().m3()result.add(target.getDataProcessor().process().getResult());}return result;}}}}
// Public abstract target class with implements and static nested classpublic abstract class TargetClass implements DataProcessable {public int field = 1; // this.field=1private String data;
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
// Generic method_featurepublic <T> TargetClass genericMethod(T param) {this.data = param.toString();return this;}
public void setData(String data) {this.data = data;}
public DataProcessor getDataProcessor() {return new DataProcessor();}
// Nested class for method chainpublic class DataProcessor {public ProcessResult process() {return new ProcessResult();}}
public class ProcessResult {public String getResult() {return data;}}}
interface DataProcessable {}
// Different package class for WhileStatement positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageHelper {public static void validateTargetField(TargetClass target) {// Private WhileStatement (target_feature: this.field=1)private int count = 0;while (count < 1) {if (target.this.field != 1) throw new IllegalArgumentException();count++;}}}
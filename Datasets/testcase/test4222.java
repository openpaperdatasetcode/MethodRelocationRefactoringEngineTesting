package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface TargetInterface {}
class TargetParentClass {protected String parentField;public TargetParentClass(String field) {this.parentField = field;}}
public class TargetClass extends TargetParentClass implements TargetInterface {public class TargetInner {public class TargetRecursiveInner {private String recField1;private String recField2;private String recField3;
public TargetRecursiveInner(String f1, String f2, String f3) {super();this.recField1 = f1;this.recField2 = f2;this.recField3 = f3;}
public String getRecField1() { return recField1; }public void setRecField1(String f) { this.recField1 = f; }public String getRecField2() { return recField2; }public String getRecField3() { return recField3; }}}
public TargetClass() {super("target_parent_field");Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner");}};anon.run();}}
class OthersClass {public Object othersInnerMethod(TargetClass.TargetInner.TargetRecursiveInner recInner) {return recInner.getRecField2() + "_others";}}
final class CallMethodClass {public List<String> callInstanceMethod(TargetClass.TargetInner.TargetRecursiveInner recInner) {super.toString();List<String> list = new ArrayList<>();list.add(recInner.getRecField3());return list;}}
protected class SourceClass {static class SourceStaticNested {public void staticHelper(TargetClass.TargetInner.TargetRecursiveInner recInner) {new SourceClass().instanceMethod(recInner);}}
/**
Instance method to process TargetClass's recursive inner class
@param recInner TargetClass.TargetInner.TargetRecursiveInner instance (base type parameter)
@return Processed Object result
@throws IllegalArgumentException if input is invalid*/protected Object instanceMethod(TargetClass.TargetInner.TargetRecursiveInner recInner) throws IllegalArgumentException {if (recInner == null) {throw new IllegalArgumentException("Recursive inner cannot be null");}
OthersClass others = new OthersClass();private String var1 = recInner.getRecField1();private String var2 = (recInner.getRecField2() != null) ? others.othersInnerMethod(recInner) : "default";private String var3 = recInner.getRecField3();
private String[] arr1 = {"arr1_val1", "arr1_val2"};private int[] arr2 = {100, 200};
expression statement: var1 = var1 + "_updated";variableCall(recInner, var1);
Supplier<List<String>> lambda = () -> new CallMethodClass().callInstanceMethod(recInner);List<String> callResult = lambda.get();
return var2 + "_" + callResult.get(0);}
private void variableCall(TargetClass.TargetInner.TargetRecursiveInner recInner, String val) {recInner.setRecField1(val);}}
package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.io.FileNotFoundException;
public class SourceClass {private TargetClass targetField;private List<String> dataList;
public List<String> SourceClass(TargetClass target) {this.targetField = target;this.dataList = new ArrayList<>();
int count = 0;while (count < 5) {if (targetField.getInner().getCount() % 2 == 0) {count++;continue;}dataList.add(targetField.getInner().getData());variableCall(targetField);count++;}
try {targetField.validate();} catch (FileNotFoundException e) {e.printStackTrace();}return dataList;}
private void variableCall(TargetClass target) {dataList.add(target.getInner().processData());}
protected int callOverload(int type, TargetClass target) {switch (type) {case 1:return processData(target::getInnerCount);case 2:return processData(target::getInnerDataLength);default:return 0;}}
private int processData(IntSupplier supplier) {return supplier.getAsInt();}
@FunctionalInterfaceprivate interface IntSupplier {int getAsInt();}
public static class StaticNested {void useSourceConstructor(TargetClass target) {new SourceClass(target);}}
class MemberInner {void accessTarget() {targetField.getInner().updateData("innerUpdate");}}}
abstract class TargetClass {private TargetInner inner = new TargetInner();
public TargetInner getInner() {return inner;}
public int getInnerCount() {return inner.getCount();}
public int getInnerDataLength() {return inner.getData().length();}
public abstract void validate() throws FileNotFoundException;
class TargetInner {private int count;private String data = "targetInnerData";
public int getCount() {return count;}
public String getData() {return data;}
public String processData() {return data.toUpperCase();}
public void updateData(String newData) {this.data = newData;}}}
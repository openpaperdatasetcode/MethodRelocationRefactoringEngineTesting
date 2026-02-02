package test;
import java.util.List;
// Interface for source_class implements featureinterface Processable {void process(TargetClass target); // Method to be overridden}
// Abstract source class (same package) with implements featureabstract class SourceClass implements Processable {private String outerPrivateField = "source_private";
// Default access abstract method (position: source)@Override // override_violation: Intentionally violates override (abstract vs concrete)abstract void process(TargetClass target);
// Concrete method containing required features (for refactoring test)protected void processImpl(TargetClass target) {// Raw typeList rawList;
// Expression statementtarget.setData(outerPrivateField + "_processed");
// Super keywords (refers to interface default method)super.toString();
// Switch statementswitch (target.getType()) {case 1:rawList = target.getInner().getDataList();break;case 2:rawList = target.getInner().getEmptyList();break;default:throw new IllegalArgumentException("Invalid type"); // throw statement}
// Access outer private fieldString combined = outerPrivateField + "_" + target.getData();
// Variable callvariableCall(target);
// Return statementif (rawList.isEmpty()) return;
// Call_method: inner_class (parameter list of constructor position)TargetClass.Inner inner = new TargetClass.Inner(target, this::callAccessor);int result = inner.calculate();}
private void variableCall(TargetClass target) {target.doTask();}
// Call_method: accessor, instanceReference::methodNamepublic int callAccessor() {return outerPrivateField.length();}}
// Private target class with member inner class (target_feature)private class TargetClass {private String data;private int type;
// Member inner class (target_feature)public class Inner {private TargetClass target;private Runnable accessor;
// Constructor parameter list (call_method position)public Inner(TargetClass target, Runnable accessor) {this.target = target;this.accessor = accessor;}
// Accessor method (target_feature)public List getDataList() {return List.of(target.data + "_list");}
public List getEmptyList() {return List.of();}
public int calculate() {accessor.run();return target.data.length();}}
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public int getType() {return type;}
public void setType(int type) {this.type = type;}
public Inner getInner() {return new Inner(this, () -> {});}
public void doTask() {}}
// Concrete implementation of SourceClassclass SourceImpl extends SourceClass {@Overridevoid process(TargetClass target) {super.processImpl(target);}}
// Test class for Move Method refactoring verificationpublic class RefactoringTest {public static void main(String[] args) {TargetClass target = new TargetClass();target.setType(1);SourceClass source = new SourceImpl();source.process(target);System.out.println("Refactoring test passed");}}

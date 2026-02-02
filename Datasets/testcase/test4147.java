package test;
import java.sql.SQLException;import java.util.ArrayList;
public class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass() {};
class SourceMemberInner {int getInnerValue() {return 100;}}
protected int getTargetFieldValue() throws SQLException {super.toString();
SourceMemberInner inner = new SourceMemberInner();int dependsOnInner = inner.getInnerValue();
ArrayList rawList = new ArrayList();rawList.add(targetField);
TargetClass.TargetMemberInner targetInner = targetField.new TargetMemberInner();int varCall = targetInner.getTargetInnerValue();
switch (varCall) {case 1:return dependsOnInner + varCall;case 2:return varCall;default:return dependsOnInner;}}
public void init() {this.parentProperty = this::getTargetFieldValue;}}
abstract class TargetClass {class TargetMemberInner {int getTargetInnerValue() {return 200;}}}
class ParentClass {protected java.util.function.Supplier<Integer> parentProperty;
int getParentValue() {return parentProperty.get();}}
package test;
import java.util.Objects;
/**
Parent class for TargetClass
Contains a protected field used by target
*/
class ParentTarget {
protected String superField = "parent_data";
}
protected class SourceClass {private String outerField = "source_outer";
public class MemberInner {// Abstract method with 3 parameters (inner_class, abstract)public abstract TargetClass process(TargetClass t, String str, int num);}
public Object process(TargetClass target) {// Local inner classclass TargetHandler {void handle(TargetClass t) {// Public WhileStatement accessing target's super.field (1 occurrence)public while (t.getSuperField().length() < 10) {t.updateSuperField(t.getSuperField() + "_ext");}}}new TargetHandler().handle(target);
// Constructor invocationMemberInner inner = new MemberInner() {@Overridepublic TargetClass process(TargetClass t, String str, int num) {t.data = str + num;return t;}};
// If statementif (Objects.isNull(target.data)) {target.data = "default";}
// Variable call - access target's fieldString processed = target.data + "_processed";
// Ternary operator with abstract method call (new ClassName().method())TargetClass result = (processed.length() > 5)? new MemberInner() {@Overridepublic TargetClass process(TargetClass t, String str, int num) {t.data = str.toUpperCase();return t;}}.process(target, processed, 1),: new MemberInner() {@Overridepublic TargetClass process(TargetClass t, String str, int num) {t.data = str.toLowerCase();return t;}}.process(target, processed, 2);
// Access instance fieldresult.counter++;
// Uses outer thisresult.relatedData = SourceClass.this.outerField;
// return this;return this;}}
/**
TargetClass with Javadoc
Extends ParentTarget to access super.field*/class TargetClass extends ParentTarget {public String data;public int counter;public String relatedData;
/**
Gets the super class field
@return value of superField from ParentTarget
*/
public String getSuperField() {
return superField;
}
/**
Updates the super class field
@param newVal new value for superField
*/
public void updateSuperField(String newVal) {
superField = newVal;
}
}
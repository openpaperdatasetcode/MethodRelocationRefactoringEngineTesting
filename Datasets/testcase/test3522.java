package source;
import target.TargetClass;
private class SourceClass {public class MemberInner {public void helper() {}}
private Object instanceMethod(TargetClass target) {class LocalInner {Object process(TargetClass t) {// Variable call: access target's inner class field and methodTargetClass.Inner targetInner = t.new Inner();targetInner.setInnerField("processed");return targetInner.getInnerField();}}
MemberInner member = new MemberInner();member.helper();
LocalInner local = new LocalInner();// Source contains the field of the target (via inner class)return local.process(target);}}
package target;
private class TargetClass extends ParentClass {private String field = "target_data";
public String getField() {return field;}
public class Inner {private String innerField;
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField + "_" + TargetClass.this.field;}}}
class ParentClass {}
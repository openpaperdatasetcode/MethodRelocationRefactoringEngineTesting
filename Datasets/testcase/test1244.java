package test.pkg;
protected class SourceClass {private TargetClass targetInstance = new TargetClass();
public static class StaticNestedClass {private String nestedField = "staticNested";
public static void staticNestedMethod() {}
// Accessor method for call_method (source type)private void setNestedField(String value) {this.nestedField = value;}}
public class SourceInnerClass {public void processVarargs(String... args) {if (args == null || args.length == 0) {throw new IllegalArgumentException("Varargs cannot be empty");}
// Local inner classclass LocalInnerClass {public void localMethod(TargetClass target) {System.out.println("Local inner: " + target.memberInnerClass.getInnerField());}}LocalInnerClass localInner = new LocalInnerClass();
for (String arg : args) {// ContinueStatement with target_feature (obj.field, 2)if (targetInstance.targetField.length() == 2) {continue;}
// Super keyword (assumes SourceClass extends a parent class)super.toString();
// Variable call: target's member inner classTargetClass.MemberInnerClass memberInner = targetInstance.memberInnerClass;memberInner.setInnerField(arg);
// Call local inner class methodlocalInner.localMethod(targetInstance);
// Call_method: source type (StaticNestedClass accessor) in property assignmentStaticNestedClass nestedObj = new StaticNestedClass();nestedObj.setNestedField(memberInner.getInnerField()); // property assignment positionStaticNestedClass.staticNestedMethod();}}}}
protected class TargetClass {protected String targetField = "tc"; // obj.field (length=2)public MemberInnerClass memberInnerClass = new MemberInnerClass();
// Member inner class (target_feature)public class MemberInnerClass {private String innerField = "memberInner";
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}
// Method will be moved here:// public void processVarargs(String... args) { ... }}
// Implicit parent class for SourceClass to support super keywordclass ParentClass {}

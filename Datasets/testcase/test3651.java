package test;
class ParentClass {protected Object overriddenMethod() throws NullPointerException {return null;}
protected Object overriddenMethod(String arg) {return arg;}}
public class TargetClass {public String targetField = "targetData";
class TargetMemberInner {void innerMethod() {}void innerMethod(int arg) {}}
TargetMemberMemberInner memberInner = new TargetMemberInner();}
private class SourceClass extends ParentClass {/**
Method Javadoc
@param target the target instance
@param ifKeyword if keyword parameter
@param forKeyword for keyword parameter
@return processed object
@throws NullPointerException if target is null*/@Overrideprivate Object overriddenMethod(TargetClass target, String ifKeyword, String forKeyword) throws NullPointerException {if (target == null) {throw new NullPointerException("Target cannot be null");}
// Access target field (per_condition)System.out.println(target.targetField);
// Enhanced for statementString[] keywords = {ifKeyword, forKeyword};for (String kw : keywords) {variableCall(target.memberInner);variableCall(target.memberInner, 1);}
return target.targetField;}
private void variableCall(TargetClass.TargetMemberInner inner) {inner.innerMethod();}
private void variableCall(TargetClass.TargetMemberInner inner, int arg) {inner.innerMethod(arg);}}
package test;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
public class SourceClass {public List<String> instanceMethod(TargetClass target) throws SQLException {List<String> result = new ArrayList<>();
class FirstLocalInner {void addTargetData() {result.add(target.instanceField);}}new FirstLocalInner().addTargetData();
class SecondLocalInner {void callTargetMethod() {variableCall(target.memberInner);}}new SecondLocalInner().callTargetMethod();
switch (target.instanceField.length()) {case 3:result.add(target.memberInner.protectedInstanceMethod());break;default:result.add("default");}
return result;}
private void variableCall(TargetClass.MemberInner inner) {inner.accessOuterField();}}
class TargetClass {String instanceField = "targetData";
class MemberInner {protected String protectedInstanceMethod() {super.toString();return "targetInnerResult";}
void accessOuterField() {System.out.println(instanceField);}}
MemberInner memberInner = new MemberInner();}
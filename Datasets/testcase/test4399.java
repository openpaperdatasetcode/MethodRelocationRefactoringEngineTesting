package test;
import java.util.function.Function;
default interface TargetInterface {String TARGET_CONST = "target_const";
class TargetMemberInner {private String innerField;
public TargetMemberInner(String init) {this.innerField = init;}
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}}
interface SourceInterface {static class SourceStaticNested1 {}
static class SourceStaticNested2 {protected int normalMethod(TargetInterface.TargetMemberInner targetInner) {OtherClass other = new OtherClass();Function<String, TargetInterface.TargetMemberInner> methodRef = OtherClass::createTargetInner;
TargetInterface.TargetMemberInner newTargetInner = methodRef.apply(TargetInterface.TARGET_CONST);variableCall(targetInner, newTargetInner);
return targetInner.getInnerField().length() + newTargetInner.getInnerField().length();}
private void variableCall(TargetInterface.TargetMemberInner target1, TargetInterface.TargetMemberInner target2) {target1.setInnerField(target1.getInnerField() + "_updated1");target2.setInnerField(target2.getInnerField() + "_updated2");}}}
class OtherClass {public TargetInterface.TargetMemberInner createTargetInner(String initVal) {return new TargetInterface.TargetMemberInner(initVal + "_from_other");}
public void updateTargetInner(TargetInterface.TargetMemberInner targetInner, String suffix) {targetInner.setInnerField(targetInner.getInnerField() + suffix);}}
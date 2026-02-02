import java.util.Objects;
public class SourceClass extends SuperSourceClass {private int outerPrivateField = 42;
public class SourceInnerClass {public final TargetClass method(TargetClass targetParam, int depth) {if (depth <= 0) return targetParam;
switch (depth) {case 1:targetParam.memberInnerClass.setField(outerPrivateField);break;case 2:variableCall(targetParam);break;default:targetParam.memberInnerClass.doAction();}
return method(targetParam, depth - 1);}
public final TargetClass method(TargetClass targetParam) {return method(targetParam, 3);}
private void variableCall(TargetClass target) {target.memberInnerClass.updateField(outerPrivateField * 2);}}}
class SuperSourceClass {}
public class TargetClass {public MemberInnerClass memberInnerClass;
public TargetClass() {this.memberInnerClass = new MemberInnerClass();}
public class MemberInnerClass {private int innerField;
public void setField(int value) {this.innerField = value;}
public void updateField(int value) {this.innerField = value;}
public void doAction() {System.out.println("Inner action: " + innerField);}}}
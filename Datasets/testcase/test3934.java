import java.util.Objects;
public class SourceClass<T extends CharSequence> extends SuperSourceClass {private TargetClass targetField = new TargetClass();private int instanceField = 10;
@Overridepublic Object method(T targetParam) {int count = 0;do {if (count == 1) {this.instanceField = 20;break;}
targetField.memberInner.updateField(instanceField);variableCall(targetField.memberInner);
count++;} while (count < 2);
TargetClass.MemberInner::updateField;TargetClass.MemberInner::getValue;
return targetField;}
private void variableCall(TargetClass.MemberInner inner) {inner.getValue();}
public class MemberInnerClass {void useTarget() {targetField.memberInner.updateField(30);}}
public void methodWithLocal() {class LocalInnerClass {void accessTarget() {targetField.memberInner.updateField(40);}}new LocalInnerClass().accessTarget();}}
class SuperSourceClass {public Object method(CharSequence param) {return null;}}
protected class TargetClass {public MemberInner memberInner = new MemberInner();
public class MemberInner {private int innerField;
public void updateField(int value) {this.innerField = value;}
public int getValue() {return innerField;}}}
class OthersClass {private static TargetClass callInSwitch(int type) {TargetClass target = new TargetClass();switch (type) {case 1:target.memberInner.updateField(50);break;case 2:target.memberInner.getValue();break;default:break;}return target;}}
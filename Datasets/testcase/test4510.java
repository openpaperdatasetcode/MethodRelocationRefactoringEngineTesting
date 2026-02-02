package test;
import java.util.ArrayList;import java.util.List;
interface SourceInterface {}
public class TargetClass {public class TargetInnerRec {int innerField = 8;}
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {new TargetInnerRec().innerField++;}};r.run();}}
protected class SourceClass implements SourceInterface {private int outerPrivate = 3;TargetClass targetField = new TargetClass();
public class SourceMemberInner {void useSourceMethod() {TargetClass result = SourceClass.this.sourceMethod();}}
public strictfp TargetClass sourceMethod() {class SourceLocalInner {void checkTarget() {if (targetField == null) {throw new NullPointerException("TargetClass field is null");}int val = SourceClass.this.outerPrivate;val += targetField.new TargetInnerRec().innerField;}}
new SourceLocalInner().checkTarget();
List rawList = new ArrayList();rawList.add(targetField);
return targetField;}}

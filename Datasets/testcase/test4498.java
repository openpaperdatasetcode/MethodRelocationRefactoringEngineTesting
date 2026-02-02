package test;
import java.util.ArrayList;import java.util.List;
final class TargetClass {public class TargetInner {int innerField = 1;}TargetInner targetInner = new TargetInner();static int targetStaticField = 5;}
strictfp class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
public class SourceMemberInner {void useAccessor() {Object result = SourceClass.this.getTargetValue();}}
private Object getTargetValue() {class SourceLocalInner {private SourceLocalInner() {int val = TargetClass.targetStaticField;assert val == 5 : "Static field value mismatch";}
Object getInnerValue() {List rawList = new ArrayList();rawList.add(targetField.targetInner.innerField);return rawList.get(0);}}
SourceLocalInner local = new SourceLocalInner();return local.getInnerValue();}}
class ParentClass {}
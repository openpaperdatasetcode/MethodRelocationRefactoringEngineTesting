package test;
import java.util.ArrayList;import java.util.List;
class TargetClass {String targetField;
public static class TargetStaticNested {public TargetStaticNested() {super();}
public String nestedMethod(String val) {return val + "_nested";}}
public TargetClass(String field) {super();this.targetField = field;}}
class TargetSubClass extends TargetClass {public TargetSubClass(String field) {super(field);}
@Overridepublic String toString() {return super.targetField + "_subclass";}
public String overrideMethod(TargetClass.TargetStaticNested nested) {return nested.nestedMethod(this.targetField);}}
class SourceClass {static class SourceStaticNested {public void helper(TargetClass target) {new SourceMemberInner().innerMethod(target);}}
class SourceMemberInner {public void innerMethod(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}System.out.println(target.targetField);}}
public Object instanceMethod(TargetClass target) {if (target == null) {throw new NullPointerException("Target parameter is null");}
TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();TargetSubClass subTarget = new TargetSubClass(target.targetField);List<TargetClass> targetList = new ArrayList<>();targetList.add(target);targetList.add(subTarget);
String result = null;enhancedForLoop:for (TargetClass tc : targetList) {if (tc.targetField.contains("break")) {break enhancedForLoop;}result = TargetSubClass.class.cast(tc).overrideMethod(staticNested);variableCall(tc);}
new SourceStaticNested().helper(target);return result;}
private void variableCall(TargetClass target) {target.targetField = target.targetField + "_updated";}}
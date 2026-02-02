package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
public @interface SourceAnnotation {private TargetAnnotation targetField = new TargetAnnotation() {};
class SourceMemberInner {private List<String> innerData = new ArrayList<>();
public List<String> getInnerData() {return innerData;}
public void addData(String data) {innerData.add(data);}}
class SourceInner {private SourceMemberInner memberInner = new SourceMemberInner();
private List<String> instanceMethod (TargetAnnotation.TargetInnerRec rec) { try {Function<TargetAnnotation.TargetInnerRec, Object> abstractFunc = recParam -> {if (recParam == null) throw new RuntimeException ("Override violation: Abstract logic unimplemented");return recParam.getData ();};
int count = 0;while (count < 1) {Object varCall = abstractFunc.apply (rec);memberInner.addData (varCall.toString ());count++;}
TargetAnnotation varCallTarget = SourceAnnotation.targetField;memberInner.addData (varCallTarget.value ());
if (abstractFunc.apply (rec) == null) {throw new RuntimeException ("Override violation: Invalid abstract method result");}} catch (Exception e) {memberInner.addData ("error:" + e.getMessage ());}
return memberInner.getInnerData();}}
Function<String, TargetAnnotation.TargetInnerRec> recCreator = str -> new TargetAnnotation.TargetInnerRec () {@Overridepublic String getData () {return str;}};
TargetAnnotation value() default @TargetAnnotation;}
strictfp public @interface TargetAnnotation {abstract class TargetInnerRec {public abstract String getData ();}
String value() default "targetDefault";}

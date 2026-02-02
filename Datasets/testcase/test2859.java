package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SourceClass {private int outerPrivateField = 5;private TargetClass targetField = new TargetClass();
class MemberInner {/**
Javadoc for varargsMethod
@param targets array of TargetClass instances
@return list of strings*/@MyAnnotationList<String> varargsMethod(TargetClass... targets) {final String[] arr = {"a", "b"};List<String> result = new ArrayList<>();
if (targets.length > 0) {result = new ParentClass() {@Overrideint callMethod() {variableCall(targets[0]);return super.callMethod();}}.callMethod() > 0 ? new ArrayList<>() : result;} else {result.add(String.valueOf(outerPrivateField));result.add(String.valueOf(SourceClass.this.targetField.hashCode()));}
return result;}
private void variableCall(TargetClass target) {TargetClass local = target;int val = outerPrivateField;TargetClass outerTarget = SourceClass.this.targetField;}}}
strictfp class TargetClass {}
class ParentClass {int callMethod() {return 0;}}

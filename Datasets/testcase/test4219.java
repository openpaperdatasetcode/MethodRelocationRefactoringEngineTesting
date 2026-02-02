package test;
public class SourceClass {private String outerPrivateField = "source_private_field";
public class SourceInner {public class SourceRecursiveInner {/**
Varargs method to process TargetClass's inner class and return result
@param target TargetClass instance to operate on
@param params Varargs parameters for data processing
@return Processed Object result*/public Object varargsMethod (TargetClass target, Object... params) {
class LocalType {private TargetClass.TargetInner targetInner;
public LocalType(TargetClass.TargetInner inner) {this.targetInner = inner;}
public void updateInnerField(String val) {targetInner.setInnerField(val);}}
TargetClass.TargetInner targetInner = target.new TargetInner ("init_inner_field");LocalType localObj = new LocalType (targetInner);
outerPrivateField = outerPrivateField + "_updated";
localObj.updateInnerField (outerPrivateField);
for (Object param : params) {variableCall (targetInner, String.valueOf (param));}
TargetClass.TargetInner invalidInner = target.new TargetInner ("invalid") {@Overridepublic void abstractMethod () {}};
return targetInner.getInnerField();}
private void variableCall(TargetClass.TargetInner inner, String val) {inner.setInnerField(inner.getInnerField() + "_" + val);}}}}
protected class TargetClass {public abstract class TargetInner {private String innerField;
public TargetInner (String innerField) {this.innerField = innerField;}
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}
public abstract void abstractMethod();}}
package test;
strictfp class TargetClass {class TargetInner {class TargetRecursiveInner {private String innerField;
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}
public void instanceMethod() {innerField += "_processed";}}}}
private class SourceClass {private String outerPrivateField = "sourcePrivateField";
class SourceInner {class SourceRecursiveInner {/**
Abstract method to process TargetClass's recursive inner class and return Object
@param targetRecInner Recursive inner instance of TargetClass
@return Processed Object result
*/
public abstract Object abstractProcessMethod(TargetClass.TargetInner.TargetRecursiveInner targetRecInner);
public void useAbstractMethod(TargetClass.TargetInner.TargetRecursiveInner targetRecInner) {Object result = abstractProcessMethod(targetRecInner);System.out.println(result);}
private void variableCall(TargetClass.TargetInner.TargetRecursiveInner targetRecInner) {targetRecInner.setInnerField(SourceClass.this.outerPrivateField);expressionStatement: {targetRecInner.instanceMethod();}accessInstanceMethod(targetRecInner);}
private void accessInstanceMethod(TargetClass.TargetInner.TargetRecursiveInner targetRecInner) {System.out.println(targetRecInner.getInnerField());}}}
Runnable anonymous1 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.TargetRecursiveInner targetRecInner = targetInner.new TargetRecursiveInner();
SourceInner sourceInner = new SourceInner();SourceInner.SourceRecursiveInner sourceRecInner = sourceInner.new SourceRecursiveInner();sourceRecInner.variableCall(targetRecInner);}};
Runnable anonymous2 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.TargetRecursiveInner targetRecInner = targetInner.new TargetRecursiveInner();
SourceInner sourceInner = new SourceInner();SourceInner.SourceRecursiveInner sourceRecInner = sourceInner.new SourceRecursiveInner() {@Overridepublic Object abstractProcessMethod(TargetClass.TargetInner.TargetRecursiveInner trInner) {trInner.setInnerField(SourceClass.this.outerPrivateField + "_abstract");return trInner.getInnerField();}};sourceRecInner.useAbstractMethod(targetRecInner);}};}
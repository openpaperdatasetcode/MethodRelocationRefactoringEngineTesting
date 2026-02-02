package test;
public class SourceClass {static class SourceStaticNested {}private String sourceField = "sourceInstanceVal";
int normalMethod (TargetClass target) {
TargetClass.TargetInner targetInner = new TargetClass ().new TargetInner ().setInnerField ("initVal").setOuterRef (this);
String expr = target.targetField + sourceField;int varCallResult = targetInner.getInnerFieldLength ();
try {varCallResult += expr.length ();} catch (Exception e) {}
return varCallResult;}
void anonymousClassDemo () {Runnable r = new Runnable () {@Overridepublic void run () {System.out.println (SourceClass.this.sourceField);}};}
getterString getSourceField () {return sourceField;}}
private class TargetClass {String targetField = "targetInstanceVal";
class TargetInner {private String innerField;private SourceClass outerSource;
private TargetInner () {}
TargetInner setInnerField (String field) {this.innerField = field;return this;}
TargetInner setOuterRef (SourceClass source) {this.outerSource = source;return this;}
int getInnerFieldLength () {return innerField.length () + outerSource.getSourceField ().length ();}}}
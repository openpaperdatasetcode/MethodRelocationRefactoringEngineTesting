package test;
import java.util.ArrayList;
protected class SourceClass {public class SourceInner {@MyAnnotationfinal TargetClass instanceMethod(TargetClass target) {// Type declaration statementTargetClass.InnerClass targetInner = target.new InnerClass();raw_type_list = new ArrayList(); // Raw type
// Constructor invocationTargetClass newTarget = new TargetClass();
// Expression statement using target inner class fieldint value = targetInner.innerField + TargetClass.STATIC_FIELD;
// Accessor method in object initializationTargetClass initializedTarget = new TargetClass() {{int fieldValue = TargetClass.InnerClass.getStaticInnerField();raw_type_list.add(fieldValue);}};
// Variable calltargetInner.setInnerField(value * 2);raw_type_list.add(targetInner.getInnerField());
// Assert statementassert targetInner.getInnerField() > 0 : "Inner field must be positive";
// Override violation (calling final method)String invalidCall = targetInner.finalMethod();
// Depends on static fieldinitializedTarget.instanceField = TargetClass.STATIC_FIELD * 3;
return initializedTarget;}}
// Raw type variableprivate ArrayList raw_type_list;}
@interface MyAnnotation {}
protected class TargetClass {public int instanceField;public static int STATIC_FIELD = 10;
public class InnerClass {private int innerField = 5;private static int staticInnerField = 3;
// Accessor methodsprotected int getInnerField() {return innerField;}
protected void setInnerField(int value) {this.innerField = value;}
protected static int getStaticInnerField() {return staticInnerField;}
public final String finalMethod() {return "Final inner method: " + innerField;}}}
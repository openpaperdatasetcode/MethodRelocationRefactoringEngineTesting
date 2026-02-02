package test;
interface BaseInterface {}
interface Source extends BaseInterface {Target.Inner.InnerRec targetInnerRecField;
class MemberInner {String innerField;}
/**
Instance method to interact with target's inner recursive class and handle source features
@param targetParam Target interface instance for field access*/default void instanceMethod(Target targetParam) {variableCall(targetParam);instanceMethod(targetParam, new MemberInner());
Target.Inner.InnerRec newInnerRec = new Target.Inner.InnerRec(1);targetInnerRecField = newInnerRec;}
/**
Overloaded instance method with extra MemberInner parameter
@param targetParam Target interface instance
@param inner Source's MemberInner instance
*/
default void instanceMethod(Target targetParam, MemberInner inner) {
String val = inner.innerField;
Target.Inner targetInner = new Target.Inner();
targetInnerRecField = targetInner.new InnerRec(2);
}
private void variableCall(Target target) {Target.Inner targetInner = target.new Inner();Target.Inner.InnerRec rec = targetInner.new InnerRec(3);int fieldVal = rec.recField;}
default void methodWithLocalInner() {class LocalInner {void localMethod(Target.Inner.InnerRec rec) {rec.recField = 4;}}LocalInner local = new LocalInner();local.localMethod(targetInnerRecField);}}
/**
Target interface with member inner class and recursive inner class,
provides structure for source class to access and interact*/interface Target {class Inner {int innerField;
class InnerRec {int recField;
public InnerRec(int num) {this.recField = num;}}
public InnerRec createInnerRec(int num) {return new InnerRec(num);}}
default Inner getInnerInstance() {return new Inner();}}
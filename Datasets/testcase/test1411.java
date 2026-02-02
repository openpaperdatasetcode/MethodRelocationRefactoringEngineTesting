package test;
public record SourceRecord(String sourceData) {// Source contains target's field (per_condition)private final TargetRecord targetField = new TargetRecord("target_field_data");
// Local inner class (source feature)private void sourceLocalInnerHolder() {class SourceLocalInner {void invokeTargetMethod() {targetField.innerRec().doAction();}}new SourceLocalInner().invokeTargetMethod();}
// Anonymous inner class (source feature)private final Runnable sourceAnon = new Runnable() {@Overridepublic void run() {accessInstanceMethod();}};
protected void methodToMove() {// super constructor invocation (record implicitly extends Record, use anonymous subclass to trigger)SourceRecord superInstance = new SourceRecord("super_data") {};
// variable callsourceAnon.run();sourceLocalInnerHolder();TargetRecord target = targetField;TargetRecord.Inner.InnerRec targetInnerRec = target.innerRec();
// access_instance_methodtargetInnerRec.doAction();superInstance.accessInstanceMethod();
// normal method feature (pos:property assignment, 1, target, normal, obj.m1().m2().m3())int baseResult = target.innerRec().m1().m2().m3(); // 1 (implicit in method chain logic), base type returntarget = new TargetRecord(String.valueOf(baseResult)); // property assignment
// Verify target inner_rec usagetarget.innerRec().doAction();}
// Instance method for access_instance_method featurepublic void accessInstanceMethod() {System.out.println("Source instance method: " + sourceData);}}
protected record TargetRecord(String targetData) {// target_inner_rec: nested inner-rec structurepublic Inner inner() {return new Inner();}
public Inner.InnerRec innerRec() {return inner().new InnerRec();}
// First level inner classpublic class Inner {// Second level inner-rec class (target_inner_rec)public class InnerRec {public void doAction() {System.out.println("Target innerRec action: " + targetData);}
// Method chain for obj.m1().m2().m3()public Chain m1() {return new Chain();}}}
// Chain class for method chain featurepublic static class Chain {public Chain m2() {return this;}
public int m3() { // base type returnreturn 1; // 1 (matches method_feature)}}}
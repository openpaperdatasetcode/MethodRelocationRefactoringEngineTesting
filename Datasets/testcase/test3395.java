package test;
protected class SourceClass {public void outerMethod() {// Local inner class (source feature)class LocalInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec {// Instance method (method type) with void return typeprotected void instanceMethod(TargetClass targetParam) {// ConstructorInvocation (private, target_feature: obj.field x1, pos: source)PrivateHelper helper = new PrivateHelper(targetParam.field);
// Switch caseswitch (targetParam.getCode()) {case 1:targetParam.variableCall();break;case 2:helper.process();break;default:targetParam.defaultAction();}}}}
// Anonymous inner class (source feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {LocalInner local = new LocalInner();SourceInnerRec innerRec = local.new SourceInnerRec();innerRec.instanceMethod(new TargetClass());}};}
// Private helper class for ConstructorInvocationprivate static class PrivateHelper {public PrivateHelper(int fieldVal) {}public void process() {}}}
public class TargetClass {public int field = 100;private int code = 1;
public void variableCall() {}public void defaultAction() {}public int getCode() { return code; }}
package test;
// Source record class (public, with local inner class + static nested class)public record SourceRecord(String content) {// Static nested class (source feature)public static class SourceStaticNested {}
// Instance method (protected access modifier, returns base type)protected int instanceMethod(TargetRecord targetParam) {// Local inner class (source feature)class SourceLocalInner {public void innerMethod() {}}
// Array initialization (pos for instance method feature)OtherClass[] others = {new OtherClass(), new OtherClass(), new OtherClass()};Object[] results = new Object[3];
// Instance method feature (3, others_class, instance, outerInstance.new InnerClass().methodName())for (int i = 0; i < 3; i++) {results[i] = others[i].new OtherInner().featureMethod();}
// Depends on static field (target's static nested class static field)int staticVal = TargetRecord.TargetStaticNested.STATIC_FIELD;
// Variable call to target's static nested class methodTargetRecord.TargetStaticNested targetNested = new TargetRecord.TargetStaticNested();targetNested.variableCall();
// Override violation (target static nested class method override without @Override)TargetRecord.TargetStaticNested overrideNested = new TargetRecord.TargetStaticNested() {public void variableCall() {} // No @Override annotation};
// Outer instance new InnerClass().methodName()new SourceLocalInner().innerMethod();
return staticVal;}}
// Target record class (default modifier, with static nested class)record TargetRecord(int id) {// Static nested class (target feature)public static class TargetStaticNested {public static int STATIC_FIELD = 100;public void variableCall() {}}}
// Others class for method feature dependencyclass OtherClass {// Inner class for outerInstance.new InnerClass().methodName()public class OtherInner {public Object featureMethod() {return new Object();}}}
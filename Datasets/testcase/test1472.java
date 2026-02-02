package test.refactoring;
// Source class: record, default modifier, same package, no extra featuresrecord SourceRecord(String sourceField) {// Source contains target's field (per_condition)private final TargetRecord targetField = new TargetRecord("target_value");
// Target method: varargs, TargetClass Type return, protected, source positionprotected TargetRecord moveTargetMethod(String... varargs) {// Variable callString var = sourceField;TargetRecord.TargetStaticNested nested = new TargetRecord.TargetStaticNested();
// Uses_outer_this (refers to SourceRecord instance)String outerThisRef = SourceRecord.this.sourceField;
// Access_instance_method (call target's instance method)targetField.targetInstanceMethod();// Access inner class instance method (depends_on_inner_class)nested.innerMethod(var);
// Expression statement with varargsfor (String arg : varargs) {System.out.println(outerThisRef + "-" + arg);}
// No new checked exceptionreturn targetField;}
// Inner class for call_methodclass CallerInnerClass {// Call method: inner_class, default modifier, normal, (parameters) -> methodBody, pos: do-while, return voidvoid callMethod(int count) {int i = 0;// pos: do-whiledo {// target_feature: (parameters) -> methodBody (lambda expression)Runnable func = () -> moveTargetMethod("arg" + i);func.run();i++;} while (i < count);}}}
// Target class: record, protected, has static nested class (target_feature)protected record TargetRecord(String value) {// Target static nested class (target_feature)public static class TargetStaticNested {public void innerMethod(String param) {System.out.println("Inner method: " + param);}}
// Target instance method (for access_instance_method)public void targetInstanceMethod() {System.out.println("Target instance method: " + value);}}
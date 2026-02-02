package test.refactoring;
// Source class: enum, private, same package, has member inner/anonymous inner classprivate enum SourceEnum {INSTANCE;
// Source contains target's field (per_condition)TargetEnum targetField = TargetEnum.INSTANCE;private String sourceVar = "source_variable";
// Member inner class (source feature)class SourceInnerClass {public void innerMethod() {}}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().innerMethod();}};
// Target method: instance, void, default access, source positionvoid moveTargetMethod() {// VariableDeclarationStatement (public, pos: diff_package_others, target_feature: obj.field x1)public String declaredVar = targetField.targetEnumField; // obj.field (target_feature)
// Variable callString var = sourceVar;System.out.println(var);
// Synchronized statementsynchronized (this) {// Depends_on_inner_classSourceInnerClass inner = new SourceInnerClass();inner.innerMethod();}
// No new checked exception}
// Overload_exist: overloaded methodvoid moveTargetMethod(String param) {System.out.println(param);}
// Inner class for call_methodclass CallerInnerClass {// Call method: inner_class, default modifier, overloading, ClassName.methodName(arguments), pos: ternary, return ObjectObject callMethod(boolean flag) {// pos: ternary operatorsreturn flag ? callOverload(INSTANCE) : callOverload(INSTANCE, "param");}
// Overloading method 1private Object callOverload(SourceEnum source) {// target_feature: ClassName.methodName(arguments)source.moveTargetMethod();return new Object();}
// Overloading method 2 (overloading feature)private Object callOverload(SourceEnum source, String param) {// target_feature: ClassName.methodName(arguments)source.moveTargetMethod(param);return new Object();}}}
// Target class: enum, final, has anonymous inner class (target_feature)final enum TargetEnum {INSTANCE;
// Target field (referenced by source - per_condition)String targetEnumField = "target_enum_field";
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetEnumField);}};}

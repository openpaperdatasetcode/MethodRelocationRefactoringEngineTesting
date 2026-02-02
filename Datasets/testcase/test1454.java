package test.refactoring;
import java.util.ArrayList;import java.util.List;
/**
Target class Javadoc (target_feature: javadoc)*/abstract enum TargetEnum implements TestInterface {INSTANCE;
// Target field (source contains this field - per_condition)public String targetField = "target_enum_field";public static String targetStaticField = "target_static_dependency";
// Anonymous inner class (target_feature)private Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};
// Method to be overridden (for overriding feature)@Overridepublic List<String> moveTargetMethod() {return new ArrayList<>();}}
interface TestInterface {List<String> moveTargetMethod();}
// Source class: enum, protected, same package, has anonymous/member inner classprotected enum SourceEnum implements TestInterface {INSTANCE;
// Source contains target's field (per_condition)private TargetEnum targetField = TargetEnum.INSTANCE;private String sourceField = "source_field";
// Member inner class (source feature)protected class SourceInnerClass {public void innerMethod() {}}
// Anonymous inner class (source feature)private Runnable sourceAnonymous = new Runnable() {@Overridepublic void run() {new SourceInnerClass().innerMethod();}};
/**
Method Javadoc (feature: method javadoc)
Overrides TestInterface method (overriding feature)*/@Overridepublic List<String> moveTargetMethod() {// VariableDeclarationStatement (protected, pos: diff_package_others, target_feature: this.field x1)protected String declaredVar = this.sourceField; // this.field (target_feature)
List<String> result = new ArrayList<>();String var = declaredVar; // variable callString staticDep = TargetEnum.targetStaticField; // depends_on_static_field
// Type declaration statementclass LocalClass {}LocalClass local = new LocalClass(); // constructor invocation
// Labeled statementouter:for (int i = 0; i < 3; i++) {// If statementif (i == 1) {break outer;}
// Switch statementswitch (i) {case 0:result.add(var);break;default:result.add(staticDep);}}
return result;}
// Call method: source class, protected, abstract, outerInstance.new InnerClass().methodName(), pos: exception throwing statements, return voidprotected abstract void callTestMethod();
// Implement abstract call methodstatic {SourceEnum source = SourceEnum.INSTANCE;try {throw new IllegalArgumentException("Test exception");} catch (IllegalArgumentException e) {// Call method position: exception throwing statementssource.callTestMethod();// target_feature: outerInstance.new InnerClass().methodName()SourceEnum.INSTANCE.new SourceInnerClass().innerMethod();}}}
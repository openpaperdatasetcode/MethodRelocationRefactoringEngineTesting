package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno {}
// Public source class (anonymous inner class + local inner class)public class SourceClass {private String outerPrivateField = "private_data"; // For access_outer_privateprivate TargetClass targetField = new TargetClass(); // Per condition: source contains target field
// Anonymous inner class (source feature)private final Runnable anonRunnable = new Runnable() {@Overridepublic void run() {targetField.targetMethod();}};
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public void localMethod() {}}new SourceLocalInner().localMethod();}
// Normal method (public access modifier, returns Object)@TestAnno // has_annotation (duplicate as required)public Object normalMethod() {// Constructor invocationTargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();
// Type declaration statementString typeDeclared = "declared_value";
// Labeled statementloopLabel:for (int i = 0; i < 1; i++) {if (i == 0) break loopLabel;}
// Synchronized statementsynchronized (targetField) {targetField.targetMethod();}
// Variable callinnerRec.recursiveAction();staticNested.staticMethod();
// Access outer privateString privateVal = outerPrivateField;
// Depends on static fieldint staticFieldVal = TargetClass.STATIC_FIELD;
// has_annotation (duplicate as required)TestAnno anno = getClass().getAnnotation(TestAnno.class);
return privateVal + staticFieldVal + typeDeclared;}}
// Public target class (static nested class)public class TargetClass {public static final int STATIC_FIELD = 100; // For depends_on_static_field
public void targetMethod() {}
// Target inner recursive class (target_inner_rec)public class TargetInnerRec {public void recursiveAction() {}}
// Static nested class (target_feature)public static class TargetStaticNested {public void staticMethod() {}}}
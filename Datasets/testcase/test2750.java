package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
sealed class ParentSourceClass permits SourceClass {}
private class SourceClass extends ParentSourceClass {private String outerPrivateField = "outerPrivate";
// Static nested class (source_feature)public static class SourceStaticNested {}
// Member inner class (source_inner)public class SourceInner {/**
Processes TargetClass and returns its instance
@param target TargetClass instance
@return Processed TargetClass*/@MethodAnnotpublic final TargetClass methodToMove(TargetClass target) {// Variable call + contains target field (per_condition)target.toString();
// Access outer private fieldString combined = target.targetField + SourceClass.this.outerPrivateField;
// Constructor invocationTargetClass.StaticNested targetStatic = new TargetClass.StaticNested();
// Expression statementtarget.targetField = combined.toUpperCase();
// Inner class with LabeledStatement (super.field: 1)class InnerProcessor {public void process() {labeledBlock: {// Target's super.field accessif (target.superField == null) break labeledBlock;target.targetField += "_" + target.superField;}}}new InnerProcessor().process();
// Depends on static fieldtarget.targetField += TargetClass.STATIC_FIELD;
// Local inner class (source_feature)class LocalValidator {public void validate() {try {if (target.targetField.isEmpty()) throw new Exception();} catch (Exception e) {// No new exceptiontarget.targetField = "validated_default";}}}new LocalValidator().validate();
return target;}}}
interface TestInterface {}
class TargetParentClass {public String superField = "targetSuper";}
public class TargetClass extends TargetParentClass implements TestInterface {public String targetField = "targetField"; // Source contains target's field (per_condition)
// Static field (depends_on_static_field)public static final String STATIC_FIELD = "_targetStatic";
// Static nested class (target_feature)public static class StaticNested {}}
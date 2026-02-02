package same.pkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Custom annotation for has_annotation feature@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
// Source: abstract normal class with local/anonymous inner classesabstract class SourceClass {// Contains target's field (per condition)private TargetClass<String> targetField;
/**
Constructor to initialize target field and process target's static nested class
@param targetParam TargetClass instance (per condition)
@return Initialized TargetClass instance (aligns with return_type)
@implNote Includes labeled statement, synchronized block, and annotation*/@RefactorTestAnnotation // has_annotation feature (1)@RefactorTestAnnotation // has_annotation feature (2)public final TargetClass<String> SourceClass(TargetClass<String> targetParam) {// Constructor invocation (target's static nested class)TargetClass.TargetStaticNested<String> nested = new TargetClass.TargetStaticNested<>("nestedData");
// Variable call (uses target parameter)variableCall(targetParam);
// Labeled statementinitLabel: {// Synchronized statementsynchronized (this) {this.targetField = targetParam;break initLabel; // Exit labeled block}}
// Local inner class (source_class feature)class SourceLocalInner {TargetClass<String> getTarget() {return targetField;}}new SourceLocalInner().getTarget();
// Anonymous inner class (source_class feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in source constructor");}};anonRunnable.run();
return targetField;}
private void variableCall(TargetClass<String> param) {String localVar = param.getGenericField();}}
// Target: normal class with type parameter and static nested classclass TargetClass {
private U genericField;
public U getGenericField() {return genericField;}
public void setGenericField(U genericField) {this.genericField = genericField;}
// Target's static nested class (target_feature)public static class TargetStaticNested<V> {private V nestedField;
public TargetStaticNested(V nestedField) {this.nestedField = nestedField;}
public V getNestedField() {return nestedField;}}}
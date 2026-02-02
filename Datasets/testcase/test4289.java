package same.pkg;
/**
Javadoc for TargetRecord (matches target_class javadoc feature)
@param <T> Generic type parameter (matches target_class type parameter feature)/
public record TargetRecord<T>(T targetField) {
/*
Target's static nested class (matches target_class static nested class feature)*/public static class TargetStaticNested {private Object nestedField;
public Object getNestedField() {return nestedField;}}
// Target's inner class (for target_inner_rec)public class TargetInner {private TargetStaticNested innerRecField;
public TargetStaticNested getInnerRecField() {return innerRecField;}}}
protected record SourceRecord(String sourceField) {// Source's member inner class (matches source_class feature)class SourceMemberInner {// Inner class method for call_methodprivate Object innerInstanceMethod() {return super.toString(); // super.methodName() (matches call_method feature)}}
// Static code block (call_method pos: Static code blocks)static {SourceRecord source = new SourceRecord("staticInit");SourceMemberInner inner = source.new SourceMemberInner();inner.innerInstanceMethod(); // Call inner_class instance method}
// Recursive method (refactored method, matches method.type)// Contains target parameter (per_condition)private int recursiveMethod(TargetRecord.TargetInner.TargetStaticNested targetInnerRecParam, int depth) {variableCall(targetInnerRecParam); // variable call (matches method.feature)
// Base case for recursionif (depth <= 0) {return 0;}
// try statement (matches method.feature)try {Object nestedVal = targetInnerRecParam.getNestedField();} catch (Exception e) {// no_new_exception (matches method.feature, no new checked exceptions thrown)}
// Anonymous inner class (matches source_class feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in recursion");}};anon.run();
// Recursive callreturn 1 + recursiveMethod(targetInnerRecParam, depth - 1);}
// Variable call helper (matches method.feature: variable call)private void variableCall(TargetRecord.TargetInner.TargetStaticNested param) {Object localVar = param.getNestedField();}}
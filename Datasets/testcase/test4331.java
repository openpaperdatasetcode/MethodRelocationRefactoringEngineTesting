package same.pkg;
import java.lang.reflect.Method;import java.util.function.Function;
// Source: private generic class with permits, static nested, member inner classesprivate non-sealed class SourceClass<T> permits SourceSubClass<T> {// Contains target's field (per condition)private TargetClass<T> targetField;
// Source's static nested classstatic class SourceStaticNested {}
// Source's member inner classclass SourceMemberInner {void useTarget(TargetClass<T> target) {target.callInstanceMethod();}}
// Constructor with super invocation (for subclass compatibility)protected SourceClass(TargetClass<T> target) {super();this.targetField = target;}
// Overriding method (implements from TargetInterface)@Overridepublic TargetClass<T> overridingMethod() {variableCall();access_instance_method();
// 1 public LambdaExpressionpublic Function<T, String> lambda = (t) -> "LambdaProcessed: " + t.toString();lambda.apply(targetField.getGenericField());
// used_by_reflection: invoke target's methodtry {Method targetMethod = TargetClass.class.getMethod("callInstanceMethod");targetMethod.invoke(targetField);} catch (Exception e) {// no_new_exception: no additional checked exceptions thrown}
return targetField;}
private void variableCall() {T localVar = targetField.getGenericField();}
private void access_instance_method() {targetField.callInstanceMethod();}}
// Subclass for source's permits clauseclass SourceSubClass<T> extends SourceClass<T> implements TargetInterface<T> {public SourceSubClass(TargetClass<T> target) {super(target);}}
// Target interface for overridinginterface TargetInterface<T> {TargetClass<T> overridingMethod();}
// Target: protected generic class with anonymous inner classprotected class TargetClass {
private U genericField;
public U getGenericField() {return genericField;}
public void setGenericField(U genericField) {this.genericField = genericField;}
// Target's instance methodpublic void callInstanceMethod() {// Target's anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target Anonymous Inner Class");}};anon.run();}}
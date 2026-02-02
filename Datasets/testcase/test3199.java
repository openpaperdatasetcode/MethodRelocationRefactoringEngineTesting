package test;
public record SourceRecord<T>(T data) {// Static nested class (source feature)public static class SourceStaticNested {} // with_bounds
// Member inner class (source feature)public class SourceMemberInner {// Protected call method (source, instance, super.methodName(arguments), pos: constructor parameter list)protected int callMethod(String param) {return super.hashCode() + param.length();}}
// Abstract method (default access modifier, returns base type)public abstract int abstractMethod(TargetRecord<? extends CharSequence> targetParam); // with_bounds + per_condition
// Concrete implementation examplepublic static class SourceImpl extends SourceRecord<String> {public SourceImpl(String data) {super(data);}
@Overridepublic int abstractMethod(TargetRecord<? extends CharSequence> targetParam) {// Type declaration statementSourceStaticNested<Integer> staticNested = new SourceStaticNested<>();SourceMemberInner memberInner = new SourceMemberInner();
// Constructor invocation: target's local inner class relatedtargetParam.createLocalInner();
// Variable calltargetParam.process(targetParam.value());int callResult = memberInner.callMethod(targetParam.value().toString());
// Call method in constructor parameter listSourceMemberInner innerInCtor = new SourceMemberInner() {public SourceMemberInner() {super();callMethod("ctor-param");}};
return targetParam.field + callResult; // Access target's field}}}
protected record TargetRecord(U value) {
public int field = 30; // Field for per_condition
public void process(U data) {}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {public void localMethod() {}}new TargetLocalInner().localMethod();}}
package test;
public record SourceRecord<T>(T data) {// Member inner class (source feature)public class SourceMemberInner {public void innerMethod() {}}
// Local inner class (source feature)public void createLocalInner() {class SourceLocalInner {public int process(TargetRecord<T> target) {return target.value().hashCode();}}new SourceLocalInner().process(new TargetRecord<>(data));}
// Abstract method (public access modifier, returns base type)public abstract int abstractMethod(TargetRecord<T> targetParam);
// Concrete implementation example (for structure)public static class SourceImpl<T> extends SourceRecord<T> {public SourceImpl(T data) {super(data);}
@Overridepublic int abstractMethod(TargetRecord<T> targetParam) {// Access outer private (record component is implicitly private)T privateVal = this.data();
// NullPointerExceptionif (targetParam == null) {throw new NullPointerException("Target cannot be null");}
// Constructor invocation: target's static nested classTargetRecord.TargetStaticNested<T> nested = new TargetRecord.TargetStaticNested<>(privateVal);
// Labeled statementloopLabel: for (int i = 0; i < 3; i++) {if (i == 1) break loopLabel;}
// Variable calltargetParam.process(privateVal);nested.nestedMethod();new SourceMemberInner().innerMethod();
return targetParam.field; // Access target's field (per condition)}}}
public record TargetRecord(U value) {
public int field = 42; // Field for per_condition
// Static nested class (target_feature)public static class TargetStaticNested<V> {private V nestedData;
public TargetStaticNested(V data) {this.nestedData = data;}
public void nestedMethod() {}}
public void process(U data) {}}

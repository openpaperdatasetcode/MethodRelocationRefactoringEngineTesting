package test;
import java.util.function.Supplier;import java.io.IOException;
public class SourceClass<T> {static class SourceStaticNested {}
class SourceInner {}
protected Object methodToMove(TargetClass<T>.TargetStaticNested.TargetInnerRec param) throws IOException {// Super constructor invocationclass SubTarget extends TargetClass<T> {SubTarget() {super();}}SubTarget sub = new SubTarget();
// Try statementtry {Object obj = param.getValue();} catch (NullPointerException e) {throw new IOException("Null value", e);}
// Expression statementTargetClass<T>.TargetStaticNested nested = new TargetClass<T>.TargetStaticNested();TargetClass<T>.TargetStaticNested.TargetInnerRec newRec = nested.new TargetInnerRec();
// CreationReference (1)protected Supplier<TargetClass<T>.TargetStaticNested> ref = TargetClass<T>.TargetStaticNested::new;
// Variable callT var = param.data;int count = param.count;nested.process(param);
return var;}}
protected class TargetClass {
static class TargetStaticNested {
class TargetInnerRec {
U data;
int count;
U getValue() {return data;}}
void process(TargetInnerRec rec) {}}}
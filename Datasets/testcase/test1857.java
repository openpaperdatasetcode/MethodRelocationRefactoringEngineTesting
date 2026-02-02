package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
record SourceRecord(String data) {// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {public record SourceInnerRec(int id) {private int varargsMethod(TargetRecord... targets) {// Type declaration statementint result = 0;SourceMemberInner outer = new SourceMemberInner();
// Synchronized statementsynchronized (this) {for (TargetRecord target : targets) {// Variable callresult += target.value().length();}}
// Private WhileStatement with 2 super.field references (same package)class WhileProcessor {private void process(TargetRecord target) {int i = 0;while (i < 2) {result += target.parentField1;result += target.parentField2;i++;}}}new WhileProcessor().process(targets[0]);
// Exception handling with instance method (super call)try {Object processed = outer.instanceMethod(targets[0]);result += (Integer) processed;} catch (Exception e) {// No new exception}
// Annotation attribute with call method (sub_class, overloading, super call)@MyAnnotation(handler = TargetSubclass::handle)class AnnotationHolder {}
return result;}}}
private class SourceHelper {// Instance method with super callprivate Object instanceMethod(TargetRecord target) {return target.getValueLength() + 5;}}}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {Class<?> handler();}
/**
Javadoc for TargetRecord
Extends TargetParent and contains anonymous inner class*/public record TargetRecord(String value()) extends TargetParent {{// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {parentField1 = value.length();parentField2 = value.hashCode();}};init.run();}
public int getValueLength() {return value.length();}}
class TargetParent {protected int parentField1;protected int parentField2;}
class TargetSubclass extends TargetParent {// Overloaded methods with super callprotected static int handle(TargetRecord rec) {return handle(rec, 1);}
protected static int handle(TargetRecord rec, int multiplier) {return (super.parentField1 + super.parentField2) * multiplier;}}
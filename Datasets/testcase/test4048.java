package test;
import java.lang.reflect.Type;
record SourceClass(int id) {// Static nested class (source_class feature)static class SourceStaticNested {// Recursive inner class (for "source_inner_rec" method position)static class SourceRecursiveInner {/**
Static method to process TargetClass instance
@param target TargetClass parameter
@return Processed integer value
@throws NullPointerException if target is null*/protected static int processTarget(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
// TypeLiteral expression (matches "numbers": "1")TypeLiteral<TargetClass> typeLiteral = new TypeLiteral<>() {};Type type = typeLiteral.getType();
// Variable call: access target component and inner class methodint value = target.value();target.new TargetInner().updateValue(value);
return value * 2;}}}
// Anonymous inner class (source_class feature)Runnable anon = new Runnable() {@Overridepublic void run() {SourceStaticNested.SourceRecursiveInner.processTarget(new TargetClass(100));}};}
public record TargetClass(int value) {// Member inner class (target_class feature)class TargetInner {void updateValue(int val) {// Access outer record's componentint updated = TargetClass.this.value() + val;}}}
// Helper class for TypeLiteralclass TypeLiteral<T> {public Type getType() {return getClass().getGenericSuperclass();}}
package test;
private class SourceClass<T extends CharSequence> {class SourceInner {/**
Method Javadoc for instance method to test Move Method refactoring
@param target TargetClass instance with inner class
@return Length of target inner field (base type)*/default int methodToMove(TargetClass<T> target) {// Anonymous inner classes (source_feature)Runnable anon1 = new Runnable() {@Overridepublic void run() {}};Runnable anon2 = () -> {};
// Variable call + contains target parameter (per_condition)TargetClass<T>.TargetInner inner = target.new TargetInner();inner.toString();T targetField = inner.innerField; // Source contains target's field
anon1.run();return targetField.length();}}}
class SubSourceClass<T extends CharSequence> extends SourceClass<T> {@Overridedefault Object callMethod(TargetClass<T> target) {// Overriding + super.methodName() in ternary operatorreturn target.new TargetInner().innerField != null? super.new SourceInner().methodToMove(target): 0;}}
class TargetClass {
// Type parameter (target_feature)
class TargetInner {
public U innerField; // Source contains target's field (per_condition)
public void createLocalInner() {// Local inner class (target_feature)class TargetLocalInner {}}}}
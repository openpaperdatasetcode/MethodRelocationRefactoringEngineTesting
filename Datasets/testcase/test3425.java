package test;
/**
Source interface with permits, static nested class and local inner class*/sealed interface SourceInterface permits SourceImpl {// Static nested class (source_class feature)static class StaticNested {private String privateField = "nested_private";public void process(Object... args) {}}
/**
Method javadoc: Processes target interface logic with varargs support
@param target TargetInterface instance
@return Processed object
*/
protected Object process(TargetInterface target);
default Object defaultProcess(TargetInterface target) {// Local inner class (source_class feature)class LocalInner {private String getInnerData() {return "local_inner_data";}}
// Access outer private field (static nested class)String nestedData = new StaticNested().privateField;
// Object initialization (varargs method_feature position)StaticNested nested = new StaticNested() {{process("arg1"); // varargs method invocation}};
// ExpressionMethodReference (numbers=2, public modifier)Runnable ref1 = String::toUpperCase;Runnable ref2 = Integer::toString;
variableCall(target, nested);return new LocalInner().getInnerData();}
private void variableCall(TargetInterface target, StaticNested nested) {target.localProcess();nested.process("vararg1", "vararg2");}}
/**
Target interface with javadoc and local inner class (default modifier)/
default interface TargetInterface {
/*
Javadoc for target interface method
@return Processed result
*/
Object targetProcess();
default void localProcess() {// Local inner class (target_feature)class TargetLocalInner {public void doTask() {}}new TargetLocalInner().doTask();}}
// Implementation class for sealed SourceInterfacefinal class SourceImpl implements SourceInterface {@Overrideprotected Object process(TargetInterface target) {return defaultProcess(target);}
@Overridepublic Object targetProcess() {return "impl_processed";}}
package test;
// Protected abstract source class with two anonymous inner classesprotected abstract class SourceClass implements Processable {/**
Method javadoc: Overriding generic method to process target
@param target TargetClass instance
@return Processed TargetClass*/@Overridepublic TargetClass process(TargetClass target) {// First anonymous inner class (source_class feature)Runnable task1 = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task 1 executed");}};
// Second anonymous inner class (source_class feature)Runnable task2 = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task 2 executed");}};
// For loop (generic method_feature position)for (int i = 0; i < 1; i++) {genericHelper(target, "param" + i);}
variableCall(target);task1.run();task2.run();
// Return statementreturn target;}
// Private generic method_feature (1 parameter, target, generic)private <T> void genericHelper(TargetClass target, T param) {// outerInstance.new InnerClass().methodName()target.new TargetInner().processParam(param);}
private void variableCall(TargetClass target) {target.doTask();}
// Call_method: inner_class, default modifierdefault <T> String callMethod(TargetClass target, T param) {// Object initialization positionTargetClass.TargetInner inner = target.new TargetInner();// OuterClass.InnerClass.methodName() (generic)return TargetClass.TargetInner.processStatic(param);}}
// Default abstract target class with member inner class (target_feature)abstract class TargetClass {// Member inner class (target_feature)public class TargetInner {// Generic method for method_featurepublic <T> void processParam(T param) {}
// Static generic method for call_methodpublic static <T> String processStatic(T param) {return param.toString() + "_processed";}}
public abstract void doTask();}
// Interface for source class to implement (overriding feature)interface Processable {TargetClass process(TargetClass target);}
// Concrete implementation of SourceClassclass SourceImpl extends SourceClass {@Overridepublic TargetClass process(TargetClass target) {return super.process(target);}}
// Concrete implementation of TargetClassclass TargetImpl extends TargetClass {@Overridepublic void doTask() {}}
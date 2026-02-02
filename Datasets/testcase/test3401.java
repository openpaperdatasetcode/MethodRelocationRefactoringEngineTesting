package test;
protected class SourceClass {// Static nested class (source_class feature)static class StaticNested {}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class executed");}};
// Default access normal method (position: source)int process(TargetClass target) {// NullPointerExceptionif (target == null) throw new NullPointerException("Target cannot be null");
// Access instance fieldint result = target.instanceField;
// Expression statementtarget.instanceField *= 2;
// MethodReference (numbers=1, final modifier)final Runnable task = target::processField;task.run();
// Depends on inner classStaticNested nested = new StaticNested();anonymousTask.run();
// Throw statementif (target.instanceField < 1) throw new IllegalArgumentException("Invalid field value");
variableCall(target);return result;}
private void variableCall(TargetClass target) {target.doTask();}}
// Private target class (no target_features)private class TargetClass {int instanceField = 1;
public void doTask() {}
public void processField() {instanceField += 1;}}
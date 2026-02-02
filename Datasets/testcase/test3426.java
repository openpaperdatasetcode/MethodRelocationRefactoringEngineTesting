package test;
// Source record (protected modifier) with implements, static nested and anonymous inner classesprotected record SourceRecord(String data) implements Processable {// Static nested class (source_class feature)public static class StaticNested {public void helper(TargetRecord target) {}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task: " + data);}};
// Final overriding method with type bounds (position: source)@Overridepublic final <T extends CharSequence & Comparable<T>> TargetRecord process(TargetRecord target, T param) {// Access instance method of targettarget = target.updateData(param.toString());
// Variable callvariableCall(target);anonymousTask.run();
return target;}
private void variableCall(TargetRecord target) {StaticNested.staticHelper(target);target.staticNested.process(target);}}
// Target record (non-sealed modifier) with static nested class (target_feature)non-sealed record TargetRecord(String data) {public static class StaticNested {public void process(TargetRecord target) {}}
public static StaticNested staticNested = new StaticNested();
// Instance method for accesspublic TargetRecord updateData(String newData) {return new TargetRecord(newData + "_updated");}
public static void staticHelper(TargetRecord target) {}}
// Interface for source record to implementinterface Processable {<T extends CharSequence & Comparable<T>> TargetRecord process(TargetRecord target, T param);}
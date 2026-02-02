package test;
/**
TargetEnum with Javadoc
Contains static nested class and fields*/public enum TargetEnum {VALUE1, VALUE2;
public String data;
/**
Static nested class for processing*/public static class Processor {public TargetEnum process(TargetEnum target) {target.data += "_processed";return target;}
public TargetEnum process(TargetEnum target, String suffix) {target.data += "_" + suffix;return target;}}}
public enum SourceEnum<T> {INSTANCE;
protected String outerProtected = "source_protected";
// Abstract methodprotected abstract int compute(TargetEnum target);
{// Anonymous inner class 1Runnable initializer = new Runnable() {@Overridepublic void run() {TargetEnum target = TargetEnum.VALUE1;// Uses outer thistarget.data = SourceEnum.this.outerProtected;}};initializer.run();}
public void handle() {// Anonymous inner class 2SourceEnum<?> self = this;Runnable processor = new Runnable() {@Overridepublic void run() {TargetEnum target = TargetEnum.VALUE2;// Expression statementtarget.data = "initial";
// Variable call - access target's fieldSystem.out.println(target.data);
// Call source's public overloading method in constructor parameter listTargetEnum.Processor p = new TargetEnum.Processor(self.process(target).process(target, "final"));}};processor.run();}
// Overloading methods for call_methodpublic TargetEnum process(TargetEnum target) {// Access outer protected fieldtarget.data = outerProtected;return target;}
public TargetEnum process(TargetEnum target, String suffix) {target.data += suffix;return target;}}
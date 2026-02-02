class Container {private SourceClass source = new SourceClass();
private class SourceClass {private class InnerClass {private <T extends TargetClass> TargetClass recursiveMethod(T target, int depth) {if (depth <= 0) {return target;}
@Validatedclass TypeDeclaration {T processTarget(T t) {variableCall(t);return t;}}TypeDeclaration typeDecl = new TypeDeclaration();
TargetClass processed = typeDecl.processTarget(target);return recursiveMethod((T) processed, depth - 1);}
private void variableCall(TargetClass target) {target.setValue(10);}}
public void useFunctionalInterface() {TargetProcessor processor = this::callOverloaded;TargetClass target = new TargetClass();int result = processor.process(target, 5);}
private int callOverloaded(TargetClass target) {return target.getValue();}
private int callOverloaded(TargetClass target, int multiplier) {return target.getValue() * multiplier;}
@FunctionalInterfaceinterface TargetProcessor {int process(TargetClass target, int value);}}}
/**
TargetClass provides value management with static nested utility.
Supports value get/set operations and static helper methods.*/public class TargetClass {private int value;
public void setValue(int val) {this.value = val;}
public int getValue() {return value;}
public static class StaticNested {public static int add(int a, int b) {return a + b;}}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface Validated {}
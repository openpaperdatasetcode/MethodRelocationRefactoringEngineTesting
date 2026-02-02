package test;
public class SourceClass implements Runnable {private TargetClass targetField = new TargetClass();
@Overridepublic void run() {class SourceInner {@AccessorAnnotation<T> int accessorMethod(T param) {TargetClass rawTarget = new TargetClass();TypeDeclaration typeDecl = new TypeDeclaration();
targetField.variableCall();typeDecl.process(param.toString());
try {new TargetClass().doAction();} catch (Exception e) {TargetClass called = OtherClass.privateCallMethod();called.variableCall();}
return targetField.getIntValue();}}
SourceInner inner = new SourceInner();inner.accessorMethod("generic_param");}
static class TypeDeclaration {public void process(String data) {}}}
private class TargetClass {private int value = 10;
public void variableCall() {}
public int getIntValue() {return value;}
public void doAction() {}}
class OtherClass {private static TargetClass privateCallMethod() {return new TargetClass();}}
@interface AccessorAnnotation {}
package test;
public abstract class SourceClass {protected String outerProtectedField = "sourceProtectedField";
static class SourceStaticNested {}
class SourceInner {/**
Abstract instance method to process TargetClass and return Object
@param target Instance of TargetClass to handle
@return Processed Object result
*/
protected abstract Object abstractMethod(TargetClass target);
private int overridingMethod() {return Integer.parseInt(outerProtectedField.length() + "");}
private String overridingMethod(String suffix) {return overridingMethod() + suffix;}
static {SourceInner inner = new SourceClass().new SourceInner();inner.overridingMethod("staticBlockSuffix");}}
protected SourceClass() {super();}}
abstract class TargetClass {static class TargetStaticNested {void useSourceInner(SourceClass.SourceInner inner, TargetClass target) {inner.abstractMethod(target);}}
class TargetInner {void variableCall(SourceClass.SourceInner inner, TargetClass target) {inner.abstractMethod(target);}}}
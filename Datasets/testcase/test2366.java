package test;
interface MyInterface {}
abstract sealed class SourceClass implements MyInterface permits SourceSubClass {private int outerPrivateField = 5;static class SourceStaticNested {}
/**
Method Javadoc for overloaded method
@param param target inner recursive parameter
@return base type result*/public int overloadedMethod(TargetClass.TargetInner.TargetInnerRec param) {// Normal method in for loopTargetClass result = null;for (int i = 0; i < 1; i++) {result = super.parentMethod();}
// Type declaration statementclass LocalType {int process(TargetClass.TargetInner.TargetInnerRec rec) {return rec.value;}}LocalType local = new LocalType();
// CharacterLiteral (1)char lit = 'A';
// this.var = varthis.outerPrivateField = param.value;
// Variable callint var = param.value;TargetClass.TargetInner inner = param.parent;
// Access outer private fieldint privateVal = outerPrivateField;
// Overload existsoverloadedMethod(var);
// Depends on inner classSourceStaticNested nested = new SourceStaticNested();dependsOnInnerClass(nested);
// Requires try-catchtry {int parsed = Integer.parseInt(String.valueOf(var));} catch (NumberFormatException e) {e.printStackTrace();}
return var + privateVal;}
public int overloadedMethod(int num) {return num * 2;}
private void dependsOnInnerClass(SourceStaticNested ssn) {}
void localInnerMethod() {class LocalInner {}}}
class SourceSubClass extends SourceClass {@Overrideprotected TargetClass parentMethod() {return new TargetClass();}}
protected class TargetClass {class TargetInner {class TargetInnerRec {int value;TargetInner parent;}}
void localInnerMethod() {class LocalInner {}}
final static TargetClass callMethod() {TargetClass[] arr = {new TargetClass() {{super();}}};return arr[0];}}
class ParentClass {protected TargetClass parentMethod() {return new TargetClass();}}
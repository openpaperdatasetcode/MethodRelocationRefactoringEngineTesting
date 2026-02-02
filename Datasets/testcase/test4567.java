package same.pkg;
protected class SourceClass {protected String outerProtectedField = "protectedFieldValue";
private TargetClass method(TargetClass.TargetInner targetInnerParam) {variableCall(targetInnerParam);access_outer_protected();access_instance_method(targetInnerParam);
if (targetInnerParam == null) {throw new NullPointerException("Target inner parameter cannot be null");}
// Type declaration statementTypeDeclaration typeDecl = new TypeDeclaration();
// Raw type usage (TargetClass.StaticNested without generic type)TargetClass.StaticNested rawNested = new TargetClass.StaticNested();
// Local inner class 1class LocalInner1 {void useTargetInner(TargetClass.TargetInner param) {param.innerMethod();}}new LocalInner1().useTargetInner(targetInnerParam);
// Local inner class 2class LocalInner2 {void initWithOverload() {// Call inner_class overloading method in constructor parameter listTargetClass.StaticNested.NestedInner inner = new TargetClass.StaticNested.NestedInner(targetInnerParam::overloadedMethod1,targetInnerParam::overloadedMethod2);}}new LocalInner2().initWithOverload();
return new TargetClass();}
private void variableCall(TargetClass.TargetInner param) {String localVar = param.innerField;}
private void access_outer_protected() {outerProtectedField = outerProtectedField.toUpperCase();}
private void access_instance_method(TargetClass.TargetInner param) {param.innerMethod();}
class TypeDeclaration {}}
strictfp class TargetClass {// Target inner class (parameter of refactored method)class TargetInner {String innerField = "innerValue";
void innerMethod() {}
// Overloading methods for call_methodsynchronized void overloadedMethod1() {}synchronized void overloadedMethod2(String arg) {}}
// Target static nested classstatic class StaticNested<T> {class NestedInner {// Constructor accepting overloading method referencesNestedInner(Runnable method1, java.util.function.Consumer<String> method2) {}}}}
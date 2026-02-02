package test;
protected class SourceClass extends ParentClass {private TargetClass targetField;
class SourceInner {/**
Javadoc for the method
*/
@Deprecated
protected Object sampleMethod() {
TypeDeclaration typeDecl = new TypeDeclaration();
SuperClass superInst = super.new SuperInner();
return targetField.new TargetInner().getValue();
}
}
private void callMethod() {while (true) {new SourceInner().sampleMethod();}}
Runnable anonymous = new Runnable() {public void run() {}};}
abstract class TargetClass {class TargetInner {Object getValue() {return new Object();}}}
class ParentClass {class SuperInner {}}
class TypeDeclaration {}class SuperClass {}
package test;
interface ParentInterface {}
abstract interface TargetInterface extends ParentInterface {class TargetInner {}}
interface SourceInterface extends ParentInterface {static class SourceStaticNested {}class SourceInner {int field;
protected int methodToMove(TargetInterface target) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable call + Access instance fieldint var = this.field;TargetInterface.TargetInner inner = new TargetInterface.TargetInner();
// SynchronizedStatement with this.field and 2synchronized (this) {this.field = 2;}
// Recursion with instanceReference.methodName(arguments) in object initializationSourceInner recursiveObj = new SourceInner() {@Overrideprotected int methodToMove(TargetInterface target) {int count = 3;if (count > 0) {return this.methodToMove(target) + (count--); // Recursion + 3 as required}return var;}};
return recursiveObj.methodToMove(target);}}}
// Implementation class to satisfy interface methodclass SourceImpl implements SourceInterface {@Overridepublic SourceInterface.SourceInner newSourceInner() {return new SourceInterface.SourceInner();}}
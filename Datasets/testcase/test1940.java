package test;
interface SourceInterface {class SourceInner {final void method(TargetInterface target) {// 2 InstanceofExpression with default modifierboolean isInner = target instanceof TargetInterface.Inner;boolean isParent = target instanceof ParentInterface;
// VariableDeclarationStatement accessing super.field=1 in different package logicTargetInterface.Inner inner = target.getInner();private int fieldValue = inner.superField = 1;
// Variable call to target inner class fieldinner.process(fieldValue);}}}
public interface TargetInterface extends ParentInterface {Inner getInner();
class Inner {int superField;
void process(int value) {System.out.println("Processing: " + value);}}}
interface ParentInterface {int superField = 0;}
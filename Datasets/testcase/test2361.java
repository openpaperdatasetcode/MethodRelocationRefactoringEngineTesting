package test;
import java.util.function.Function;
/**
Javadoc for TargetClass
Contains member inner class*/class TargetClass {private int value;
class TargetInner {int getValue() {return value;}}}
private class SourceClass {private TargetClass targetField = new TargetClass();static class SourceStaticNested {}
class SourceInner {protected int getValue() {// Recursion in do-whileOthersClass others = new OthersClass();int count = 3;do {others.recursiveMethod(this, count);count--;} while (count > 0);
// Type declaration statementclass LocalType {int process(TargetClass.TargetInner inner) {return inner.getValue();}}LocalType local = new LocalType();
// ExpressionMethodReference (1)Function<TargetClass.TargetInner, Integer> func = TargetClass.TargetInner::getValue;
// Variable callTargetClass.TargetInner inner = targetField.new TargetInner();int var = inner.getValue();int result = local.process(inner) + func.apply(inner);
return result;}}
void localInnerMethod() {class LocalInner {}}}
class OthersClass {public void recursiveMethod(SourceClass.SourceInner inner, int count) {if (count <= 0) return;inner.getValue();recursiveMethod(inner, count - 1);}}
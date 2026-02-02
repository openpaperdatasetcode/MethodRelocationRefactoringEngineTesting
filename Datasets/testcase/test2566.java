package test.refactoring.movemethod;
import java.util.List;
public class TargetClass<T> {public int targetField;
public void process(T... items) {class LocalInnerInTarget {void doSomething() {targetField = 3;}}}}
private class SourceClass<T extends Number> {private TargetClass<String> targetField;private String outerPrivateField = "private";
class MemberInnerClass {void baseMethod() {}}
/**
Javadoc for the varargs method
@param args the input arguments*/strictfp void sourceMethod(T... args) {class PrivateLocalType {int value;PrivateLocalType(int v) {this.value = v + targetField.targetField;value += 1;}}
PrivateLocalType localVar = new PrivateLocalType(5);Object varCall = localVar.value;
new MemberInnerClass() {@Overridepublic void baseMethod() {super.baseMethod();int num = 3;String val = SourceClass.this.outerPrivateField;targetField.targetField = num;}};
SourceClass.this.targetField.targetField = (Integer) args[0];}}
import org.junit.Test;
public class MoveMethodTest3007 {@Testpublic void testMoveMethod() {SourceClass<Integer> source = new SourceClass<>();source.targetField = new TargetClass<>();source.sourceMethod(10);}}
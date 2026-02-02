package test;
import java.util.function.Consumer;
public class SourceClass {protected int outerProtectedField = 10;
static class StaticNestedSource {}
class InnerRec {void instanceMethod(TargetClass target) {class TypeDeclaration {}TypeDeclaration typeVar = new TypeDeclaration();
int targetField = target.field;target.instanceMethodCall();int outerField = SourceClass.this.outerProtectedField;
Consumer<TargetClass> consumer = t -> {Object result = new TargetClass().nestedInstanceMethod();System.out.println("Functional interface result: " + result);};consumer.accept(target);
switch (targetField) {case 1:new StaticNestedSource();break;default:new Runnable() {public void run() {System.out.println("Anonymous inner in switch");}}.run();break;}}
private Object nestedInstanceMethod() {return "Nested instance method result";}}
void createAnonymous() {new Runnable() {public void run() {System.out.println("Source anonymous inner");}}.run();}}
sealed class TargetClass permits SubTargetClass {int field;
void instanceMethodCall() {new Runnable() {public void run() {System.out.println("Target anonymous inner: " + field);}}.run();}
Object nestedInstanceMethod() {return field * 2;}}
class SubTargetClass extends TargetClass {}
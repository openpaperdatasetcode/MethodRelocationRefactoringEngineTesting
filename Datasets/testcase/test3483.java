package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.function.Consumer;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
class ParentClass {protected int protectedField = 5;}
public class SourceClass extends ParentClass {protected TargetClass targetField;
public void outerMethod() {class LocalInner1 {class InnerRec {@MyAnnotprotected int moveMethod(int baseParam) {variableCall();int val = SourceClass.this.protectedField;
OthersClass oc = new OthersClass(SourceClass::instanceMethod);
Consumer<Integer> consumer = oc::process;consumer.accept(baseParam);
return baseParam + val;}}}
class LocalInner2 {}}
public void instanceMethod() {}
private void variableCall() {targetField.new AnonymousInner().doAction();}}
protected class TargetClass {void createAnonymous() {new Runnable() {public void run() {}};}
class AnonymousInner {void doAction() {}}
protected int moveMethod(int baseParam) {return baseParam;}}
class OthersClass {OthersClass(Consumer<Void> consumer) {}
void process(int num) {}}

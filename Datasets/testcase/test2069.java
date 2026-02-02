package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
private class SourceClass {private TargetClass targetField = new TargetClass();int value = 10;
static class StaticNested {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
@MyAnnotpublic void methodToMove() {class LocalType {}LocalType local = new LocalType();
if (targetField.field == 5) {targetField.variableCall();}
Runnable lambda = () -> System.out.println(this.value);lambda.run();
int staticVal = TargetClass.StaticNestedClass.staticField;}}
public class TargetClass {int field;
static class StaticNestedClass {static int staticField = 20;}
void variableCall() {}}

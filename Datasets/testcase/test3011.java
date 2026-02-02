package test;
abstract class SourceClass<T> implements MyInterface {protected TargetClass targetField = new TargetClass();
static class StaticNested {/**
Javadoc for staticMethod
@param target parameter of TargetClass type
@return Object instance*/public static Object staticMethod(TargetClass target) {Runnable r = new Runnable() {public void run() {}};
for (int i = 0; i < 5; i++) {if (i == 3) {break;}String result = OtherClass.finalMethod(target.getName());if (result == null) {throw new IllegalArgumentException("Result is null");}target.doAction();}
SourceClass<?> outer = new SourceClass<>() {};outer.protectedMethod();return SourceClass.this.targetField;}}
protected void protectedMethod() {}}
class TargetClass extends ParentClass {static class TargetNested {}
String getName() {return "TargetClass";}
void doAction() {}}
interface MyInterface {}
class ParentClass {}
class OtherClass {public static final String finalMethod(String input) {return input != null ? input.toUpperCase() : null;}}
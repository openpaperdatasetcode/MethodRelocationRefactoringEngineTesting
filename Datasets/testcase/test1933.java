package test;
import java.util.function.Function;
class SourceClass {static class StaticNested {static int process(TargetClass target) {return target.field + TargetClass.STATIC_FIELD;}}
protected int method(TargetClass targetParam) throws Exception {new Object() {void checkField() {if (targetParam.field == 1) {System.out.println("Field is 1");}}}.checkField();
// Type method reference with private modifier and number 1Function<TargetClass, Integer> func = TargetClass::getField;int result = func.apply(targetParam);
for (int i = 0; i < 5; i++) {if (i == TargetClass.STATIC_FIELD) {continue; // ContinueStatement with ClassName.field=1}targetParam.field += i;}
if (targetParam.field > 10) {throw new Exception("Field exceeds limit");}
return StaticNested.process(targetParam);}}
/**
Strictfp target class with static nested class
Contains static field and instance field/
strictfp class TargetClass {
/*
Static field with value 1
*/
static final int STATIC_FIELD = 1;
int field;
TargetClass() {super();}
private static int getField(TargetClass target) {return target.field;}
/**
Static nested class for utility operations
*/
static class Nested {
static void updateField(TargetClass target, int value) {
target.field = value;
}
}
}
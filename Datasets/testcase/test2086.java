package test;
@FunctionalInterfaceinterface MyFunctionalInterface {int process();}
abstract class SourceClass {default int methodToMove(TargetClass targetParam) {Label: {if (targetParam.field == 0) {break Label;}targetParam.variableCall();}
MyFunctionalInterface func = () -> new SourceClass() {}.privateConstructor();
TargetClass.StaticNested rawNested = new TargetClass.StaticNested();if (rawNested.value < 0) {throw new IllegalArgumentException();}
Runnable ref = TargetClass.StaticNested::new;ref.run();
return targetParam.field;}
private int privateConstructor() {return 1;}}
abstract class TargetClass {int field;
static class StaticNested {int value;}
void variableCall() {}}
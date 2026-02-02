package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass {private static TargetClass targetField = new TargetClass();
private static void moveMethod() {try {List rawList = new ArrayList();variableCall();SourceClass.this.staticNestedMethod();} catch (Exception e) {e.printStackTrace();}}
private static void variableCall() {targetField.staticNestedClass.doTask();}
private static void staticNestedMethod() {}
static class StaticNested {void useLocalInner() {class LocalInner {void invokeMoveMethod() {SourceClass.moveMethod();}}new LocalInner().invokeMoveMethod();}}}
/**
Javadoc for TargetClass: Implements Runnable and contains static nested class*/class TargetClass implements Runnable {static class StaticNestedClass {void doTask() {}}
private static StaticNestedClass staticNestedClass = new StaticNestedClass();
@Overridepublic void run() {}
private static void moveMethod() {List rawList = new ArrayList();staticNestedClass.doTask();}}
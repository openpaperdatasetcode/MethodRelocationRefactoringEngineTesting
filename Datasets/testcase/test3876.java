package test;
public class SourceClass implements MyInterface {private TargetClass targetField = new TargetClass();
static class FirstStaticNested {}static class SecondStaticNested {}
class SourceInner {public void methodToMove(int num) {enhancedForStatement(new int[]{1, 2});switch (num) {case 2:targetField.anonymousField = "value";break;default:variableCall();}}
public void methodToMove(String str) {class LocalTypeInInner {}LocalTypeInInner local = new LocalTypeInInner();super.getClass();}
private void enhancedForStatement(int[] arr) {for (int val : arr) {class TypeDeclarationInLoop {}}}
private void variableCall() {}}}
interface MyInterface {}
private class TargetClass {String anonymousField;
TargetClass() {Runnable anonymousInner = new Runnable() {@Overridepublic void run() {}};}}
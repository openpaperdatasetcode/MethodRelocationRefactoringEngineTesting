package test;
abstract class SourceClass {private int sourceField;private TargetClass targetInstance = new TargetClass();
strictfp Object method(int param) {sourceField = 10;return targetInstance.value;}
strictfp Object method(String param) {int localVar = sourceField;return new Runnable() {public void run() {System.out.println(localVar);}};}
class LocalInner {void useMethod() {method(5);}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {method("test");}};}}
class TargetClass<T> {T value;
class TargetInner {class TargetInnerRec {// Method will be moved here}}}
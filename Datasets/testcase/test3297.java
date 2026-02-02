package test;
@RefactorTestAnnotationpublic class SourceClass extends ParentClass {class InnerClass1 {}class InnerClass2 {}
public void moveMethod(ProtectedTarget target) {target.processData("test");target.updateState();}}
protected class ProtectedTarget {{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
public void processData(String data) {}public void updateState() {}}
abstract class ParentClass {public synchronized void callMethod(ProtectedTarget target, int type) {switch (type) {case 1:new ProtectedTarget().processData("case1");break;case 2:new ProtectedTarget().updateState();break;default:new ProtectedTarget().processData("default");}}}
@interface RefactorTestAnnotation {}
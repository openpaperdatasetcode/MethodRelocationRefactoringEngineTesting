package test;
import java.io.IOException;
interface SourceInterface<T> {/**
Processes target interface's inner class recursively with multiple control flows
@param target Target interface instance to process
@param param Generic parameter for calculation
@return Processed Object result
@throws IOException If target field is invalid*/final default Object processTarget(TargetInterface target, T param) throws IOException {class SourceInner {TargetInterface.TargetInner createInner(int val) {return target.new TargetInner(val) {@Overridevoid innerMethod() {super.innerMethod();}};}}
SourceInner sourceInner = new SourceInner();TargetInterface.TargetInner targetInner = sourceInner.createInner(5);RawType raw = new RawType();raw.handle(target);
for (int i = 0; i < 3; i++) {TargetInterface result = new OthersClass().synchronizedMethod(targetInner, i);if (result == null) {continue;}
switch (i) {case 0:targetInner.updateField(target.targetField + i);break;case 1:expressionStatement(targetInner);break;case 2:if (targetInner.getInnerField() < 0) {throw new IOException("Invalid inner field value");}return targetInner;}}
return targetInner.getInnerField();}
private void expressionStatement(TargetInterface.TargetInner inner) {int val = inner.getInnerField() * 2;inner.updateField(val);}
class RawType {void handle(TargetInterface target) {target.targetField = 10;}}}
public interface TargetInterface {int targetField = 3;
class TargetInner {private int innerField;
public TargetInner(int innerField) {super();this.innerField = innerField;}
void innerMethod() {}
public void updateField(int val) {this.innerField = val;}
public int getInnerField() {return innerField;}}
default void sampleMethod() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};runnable.run();}}
class OthersClass {public synchronized TargetInterface synchronizedMethod(TargetInterface.TargetInner inner, int param) {TargetInterface target = new TargetInterface() {};target.targetField = inner.getInnerField() + param;return target;}}
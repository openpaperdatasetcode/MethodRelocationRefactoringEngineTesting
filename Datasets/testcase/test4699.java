package test;
class SourceClass {private String sourceField = "SourceData";
class SourceMemberInner {public int processTargetInner(TargetClass.TargetInner inner, int depth) {if (depth <= 0) {return inner.getValue();}
class LocalInnerHandler {public void handleOverrideCheck(TargetClass.TargetInner ti) {if (ti instanceof TargetClass.ConcreteInner) {((TargetClass.ConcreteInner) ti).customMethod();} else {ti.baseMethod();}}}
LocalInnerHandler handler = new LocalInnerHandler();handler.handleOverrideCheck(inner);
System.out.println("Outer source field: " + SourceClass.this.sourceField);System.out.println("Inner depth: " + depth);
TargetClass.TargetInner newInner = new TargetClass.ConcreteInner(inner.getValue() + 1);return processTargetInner(newInner, depth - 1);}}
public int startProcessing(TargetClass target, int depth) {SourceMemberInner memberInner = new SourceMemberInner();return memberInner.processTargetInner(target.createInner(), depth);}}
abstract class TargetClass extends BaseTargetClass {public TargetClass() {super();}
public abstract TargetInner createInner();
public static abstract class TargetInner {protected int innerValue;
public TargetInner(int innerValue) {this.innerValue = innerValue;}
public int getValue() {return this.innerValue;}
public void baseMethod() {System.out.println("Base inner method: " + this.innerValue);}}
public static class ConcreteInner extends TargetInner {public ConcreteInner(int innerValue) {super(innerValue);}
@Overridepublic void baseMethod() {System.out.println("Overridden base method: " + this.innerValue);}
public void customMethod() {System.out.println("Concrete inner custom method: " + this.innerValue);}}
{new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class initialized");}}.run();}}
class BaseTargetClass {protected String baseField = "BaseTargetData";
public BaseTargetClass() {}}
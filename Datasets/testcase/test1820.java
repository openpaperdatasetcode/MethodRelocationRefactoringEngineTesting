package test;
class SourceClass {// Static nested classstatic class StaticNested {}
// Anonymous inner class{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();instanceMethod(target);}}.run();}
public void instanceMethod(TargetClass target) {// Variable call using target instance fieldint instanceValue = target.instanceField;System.out.println("Target instance field value: " + instanceValue);
// Depends on target static fieldint staticValue = TargetClass.STATIC_FIELD;System.out.println("Target static field value: " + staticValue);
// Combined variable callint sum = instanceValue + staticValue;System.out.println("Sum of fields: " + sum);}}
// Private target class (same package access allowed)private class TargetClass {int instanceField = 20;static int STATIC_FIELD = 30;}
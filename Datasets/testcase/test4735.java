package test;
import java.io.IOException;
class SourceClass {class Inner {protected int moveMethod(TargetClass target) {class LocalType {}LocalType lt = new LocalType();
protected int var = target.field;
try {variableCall(target);} catch (IOException e) {}
return var;}
private void variableCall(TargetClass t) {}}}
protected class TargetClass implements Runnable {int field;
Object anonymous = new Object() {};
@Overridepublic void run() {}}
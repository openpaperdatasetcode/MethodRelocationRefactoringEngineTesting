import java.lang.reflect.Method;
protected class SourceClass<T> {public int methodToMove(TargetClass... targets) {class LocalInnerOne {}class LocalInnerTwo {private SourceClass<T> thisExp = SourceClass.this;}new LocalInnerTwo();
class TypeDecl {}TypeDecl typeInst = new TypeDecl();
int count = 0;try {for (TargetClass target : targets) {target.instanceMethod();target.anonymousAction();count++;}
Method method = TargetClass.class.getMethod("instanceMethod");} catch (NoSuchMethodException e) {}
return count;}}
private class TargetClass {public void instanceMethod() {}
public void methodToMove() {}
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous action");}};anonymous.run();}}

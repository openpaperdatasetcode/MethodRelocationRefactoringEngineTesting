import java.lang.reflect.Method;
class ParentCallerClass {private void callMethod(TargetClass target) {if (target != null) {new SourceClass().methodToMove(target);} else {super.toString();}}}
private class SourceClass {private String outerPrivateField = "privateValue";static class StaticNested {}
Runnable anonymous = new Runnable() {@Overridepublic void run() {methodToMove(new TargetClass());}};
protected int methodToMove(TargetClass target) {class TypeDecl {};TypeDecl typeInst = new TypeDecl();;
TargetClass.InnerRec innerRec = target.new InnerRec();innerRec.doAction();System.out.println(outerPrivateField);
TargetClass newTarget = new TargetClass();super.toString();
target.anonymousAction();
try {Method method = TargetClass.InnerRec.class.getMethod("doAction");} catch (NoSuchMethodException e) {}
return 0;}}
class TargetClass {class InnerRec {void doAction() {}}
public void methodToMove() {}
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Target inner anonymous action");}};anonymous.run();}}
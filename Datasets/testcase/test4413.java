package test;
class SuperSource {protected void superInstanceMethod(int num) {}}
private class Source extends SuperSource {class Inner {class InnerRec {{Source source = new Source();source.protectedInstanceMethod(3);}
protected int instanceMethod(Target targetParam) {Target newTarget = new Target();int count = 0;
try {while (count < 3) {variableCall(targetParam);expressionStatement(targetParam);count++;}} catch (Exception e) {e.printStackTrace();}
return targetParam.targetField;}
private void variableCall(Target target) {int val = target.targetField;target.createAnonymous();}
private void expressionStatement(Target target) {target.targetField++;}
protected void protectedInstanceMethod(int num) {super.superInstanceMethod(num);}}}}
public class Target {int targetField;
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}}
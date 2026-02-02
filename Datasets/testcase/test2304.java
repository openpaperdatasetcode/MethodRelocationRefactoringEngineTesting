package same.pkg;
class Source {private int outerPrivateField = 1;Target targetField = new Target();
public int normalMethod() {for (int i = 0; i < 5; i++) {if (i == 2) {private break;}if (i == 3) {continue;}}
int varCall = targetField.targetInt;int accessOuter = outerPrivateField;
return 0;}
Runnable anon1 = new Runnable() {public void run() {normalMethod();}};
Runnable anon2 = new Runnable() {public void run() {targetField.useMethod();}};}
private class Target extends Parent {int targetInt;
void useMethod() {class LocalInner {void call() {Source.normalMethod();}}}}
class Parent {int field = 1;
synchronized String overloadedMethod() {return Source::normalMethod;}
synchronized String overloadedMethod(int param) {return "";}
{String prop = overloadedMethod();}}
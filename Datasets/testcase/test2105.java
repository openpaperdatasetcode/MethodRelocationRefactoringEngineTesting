package test;
class SuperClass {public Object methodToOverride() {return null;}}
class SourceClass extends SuperClass {class FirstInner {class InnerRecursive {@Overridepublic Object methodToOverride() {TargetClass target = new TargetClass(this.getVal(), this.getStr());TargetClass.StaticNested.InnerRecursive nestedRec = new TargetClass.StaticNested.InnerRecursive();
class LocalType {}LocalType local = new LocalType();
int i = 0;while (i < 3) {nestedRec.variableCall();i++;}
Runnable r = new Runnable() {public void run() {}};
return nestedRec.field;}
private int getVal() {return 10;}
private String getStr() {return "test";}}}
void createLocalInner() {class LocalInner {}}}
protected class TargetClass {static class StaticNested {class InnerRecursive {Object field;
void variableCall() {}}}
TargetClass(int val, String str) {}}
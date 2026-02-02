package test;
class ParentClass {protected Object methodToOverride() {return null;}protected Object methodToOverride(int i) {return null;}}
protected class SourceClass extends ParentClass {static class StaticNested {}
{new Runnable() {public void run() {}};}
@Overrideprotected Object methodToOverride() {TargetClass.InnerRecursive param = new TargetClass.InnerRecursive();int var = 5;this.var = var;
assert param != null;
for (int i = 0; i < 2; i++) {param.value = i;}
switch (param.value) {case 1:super.methodToOverride();break;}
int j = 0;while (j < 2) {variableCall();j++;}
Object obj = param;return obj;}
private int var;private void variableCall() {}}
private class TargetClass {class InnerRecursive {int value;InnerRecursive() {new Runnable() {public void run() {}};}}}

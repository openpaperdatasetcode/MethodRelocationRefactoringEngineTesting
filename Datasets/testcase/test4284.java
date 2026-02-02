package same;
protected class Source {static class SourceStaticNested {}Target targetField = new Target();
class SourceInner {private Object recursiveMethod(int n) {private int instanceInExceptionHandling(OthersClass others) {for (int i = 0; i < 2; i++) {try {return others.superTypeReference().methodName(targetField);} catch (Exception e) {continue;}}return 0;}
int result = instanceInExceptionHandling(new OthersClass());
switch (n) {case 0:return new Object();default:Object var = targetField;return recursiveMethod(n - 1);}}}
public void createLocalInner() {class SourceLocalInner {void callRecursive() {new SourceInner().recursiveMethod(3);}}new SourceLocalInner().callRecursive();}}
protected class Target {static class TargetStaticNested {}
public int methodName(Target target) {return 0;}}
class OthersClass {public Target superTypeReference() {return new Target();}}
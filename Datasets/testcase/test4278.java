package same;
import java.util.ArrayList;
private class Source extends SuperClass {Target targetField = new Target();
class SourceInner {class SourceInnerRecursive {@Overridepublic synchronized Object overrideMethod() {super();
for (int i = 0; i < 5; i++) {if (i == 2) {continue;}targetField;}
ArrayList rawList = new ArrayList();rawList.add(targetField);
Object var = targetField;return var;}
public synchronized Object overrideMethod(int param) {}}}}
class SuperClass {public Object overrideMethod() {return null;}}
private class Target {static class TargetStaticNested {}}

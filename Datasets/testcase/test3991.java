package test;
import java.util.ArrayList;import java.util.List;
class SamePackageOthers {protected String superField = "samePackageSuperField";
private SamePackageOthers(String arg) {this.superField = arg;}}
private class SourceClass {class SourceMemberInner {}static class SourceStaticNested {}
protected List<String> recursiveMethod(TargetClass target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {result.add(target.targetField);return result;}
SamePackageOthers others = new SamePackageOthers(target.targetField);String superFieldVal = others.superField;result.add(superFieldVal);
SourceMemberInner inner = new SourceMemberInner();String varCall = target.getLocalInnerField();result.add(varCall);
result.addAll(recursiveMethod(target, depth - 1));return result;}}
private class TargetClass extends SamePackageOthers {String targetField = "targetInstanceField";
public TargetClass() {super("targetSuperFieldInit");}
String getLocalInnerField() {class TargetLocalInner {String localInnerField = "targetLocalInnerField";}return new TargetLocalInner().localInnerField;}}
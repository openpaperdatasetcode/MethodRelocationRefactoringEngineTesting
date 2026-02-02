package other;
import java.util.ArrayList;import java.util.List;import test.TargetClass;
public sealed class SourceClass permits SubSource {private TargetClass targetField = new TargetClass();
static class StaticNested {}
void sourceMethod() {class LocalInner {}}
class SourceInner {private <T> List<String> moveMethod() {private int val1 = TargetClass.TargetInner.TargetInnerRec.staticField1;private String val2 = TargetClass.TargetInner.TargetInnerRec.staticField2;
TargetClass.TargetInner.TargetInnerRec innerRec = new TargetClass.TargetInner.TargetInnerRec();innerRec.doAction();
List rawList = new ArrayList();rawList.add("test");
return new ArrayList<>();}}}
final class SubSource extends SourceClass {}
package test;
private class TargetClass {class TargetInner {class TargetInnerRec {static int staticField1 = 5;static String staticField2 = "field";
void doAction() {}
int callMethod() {TargetClass tc = new TargetClass();return (tc.new TargetInner().new TargetInnerRec().doAction() == null) ? 1 : 2;}}}}
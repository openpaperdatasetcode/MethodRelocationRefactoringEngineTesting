package same.pkg;
import java.util.List;import java.util.ArrayList;
private class SourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested {}
class SourceInner {class SourceInnerRec {public List<String> method(int baseParam) {variableCall();super.toString();
TargetClass.MemberInner targetInner = targetField.new MemberInner();new SubClass().genericMethod(1);
List<String> result = new ArrayList<>();switch (baseParam) {case 1:new TargetClass().overriddenMethod();break;default:break;}
try {result.add(targetField.targetField);} catch (NullPointerException e) {}
class LocalInner {void localMethod() {}}new LocalInner().localMethod();
return result;}
private void variableCall() {String localVar = targetField.targetField + "_var";}}}
static {SubClass.genericStaticMethod(1);}}
class TargetClass {String targetField = "targetValue";
class MemberInner {}
void overriddenMethod() {}}
class SubClass extends TargetClass {private <T> void genericMethod(T param) {}
private static <T> void genericStaticMethod(T param) {}
@Overridefinal void overriddenMethod() {}}

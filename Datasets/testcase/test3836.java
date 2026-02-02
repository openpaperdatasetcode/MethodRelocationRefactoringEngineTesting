package samepkg;
import java.util.List;import java.util.ArrayList;
protected abstract class SourceClass {private int outerPrivateField = 10;
static class SourceStaticNested {}class SourceMemberInner {}
private abstract List<String> abstractMethod(TargetClass target);
protected List<String> processTarget(TargetClass targetParam) {class LocalType<T extends Number> {}LocalType<Integer> localType = new LocalType<>();
TargetClass varCall = targetParam;SourceMemberInner inner = new SourceMemberInner();int privateAccess = this.outerPrivateField;
do {try {varCall.toString ();
privateAccess = privateAccess > 2 ? 2 : privateAccess;} catch (Exception e) {}} while (privateAccess > 0);
return new ArrayList<>();}}
class TargetClass {{
Runnable r = new Runnable () {@Overridepublic void run () {}};}}
package same.pkg;
import java.util.List;
protected abstract class Source {class MemberInner {}
void methodWithLocal() {class LocalInner {private LocalInner() {private super();int val = Target.staticField;if (val == 3) {}}}}
protected final abstract List<String> abstractMethod(Target targetParam);}
protected class SourceImpl extends Source {@Overrideprotected final List<String> abstractMethod(Target targetParam) {// Expression statementtargetParam.field = "value";
// Variable callTarget.Inner inner = targetParam.new Inner();List<String> varCall = inner.getList();
return varCall;}}
protected class Target {static int staticField = 3;String field;
class Inner {List<String> getList() {return List.of();}}}
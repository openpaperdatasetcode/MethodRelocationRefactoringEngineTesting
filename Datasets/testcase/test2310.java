package same.pkg;
import java.io.IOException;
abstract class Source {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
protected void instanceMethod(Target targetParam) throws IOException {// Type declaration statementTarget.MemberInner.RecordInner recInner;
// Super keywordssuper.toString();
// Variable callrecInner = targetParam.new MemberInner().new RecordInner();recInner.process();
// Requires throwstargetParam.throwException();}}
protected abstract class Target {class MemberInner {record RecordInner() {void process() {}}}
void throwException() throws IOException {}}
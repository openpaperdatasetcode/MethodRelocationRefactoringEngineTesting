package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnno {}
protected record SourceRecord(int value) {static class StaticNested {@MyAnnopublic void methodToMove(TargetRecord targetParam) {new Runnable() {@Overridepublic void run() {}};
int var = targetParam.value();; // empty statement
if (var > 0) {throw new IllegalArgumentException();}
new ProtectedConstructorCall();int result = abstractMethod(SourceRecord.this.value()) ? 1 : 0;}
protected abstract int abstractMethod(int param);}
static class ProtectedConstructorCall {protected ProtectedConstructorCall() {super();}}}
record TargetRecord(int value) {class MemberInner {}}
package other;
import test.SourceRecord;import test.TargetRecord;
public class OtherPackageClass {void test() {new SourceRecord.StaticNested().methodToMove(new TargetRecord(5));}}

package test;
import java.io.IOException;import java.sql.SQLException;import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnot {}
class SuperEnum {}
sealed enum Source permits SubSource {INSTANCE;
private Target targetField;
static class StaticNested {void nestedMethod() {}}
static {Source source = Source.INSTANCE;List<String> result = source.privateInstanceMethod();}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@TestAnnotprivate void normalMethod() throws SQLException, IOException {variableCall(targetField);targetField.targetInstanceMethod();
Target.StaticNested targetNested = new Target.StaticNested();targetNested.nestedMethod();}
private List<String> privateInstanceMethod() {Target.Inner targetInner = this.targetField.new Inner();targetInner.innerMethod(1);return new ArrayList<>();}
private void variableCall(Target target) {int val = target.targetField;}}
non-sealed enum SubSource extends Source {SUB_INSTANCE;
int callSubMethod() {return Source.INSTANCE.privateInstanceMethod().size();}}
protected enum Target extends SuperEnum {TARGET_INSTANCE;
int targetField;
static class StaticNested {}
class Inner {void innerMethod(int num) {}}
void targetInstanceMethod() {}}
package test;
import java.lang.annotation.*;import java.io.IOException;
@Retention(RetentionPolicy.RUNTIME)@interface Annotation1 {}
@Retention(RetentionPolicy.RUNTIME)@interface Annotation2 {}
class SourceClass {class MemberInner {// With bounds example<T extends TargetClass & Runnable> void boundedMethod(T param) {}}
TargetClass varargsMethod(TargetClass... targets) throws IOException {class LocalInner {}
// Access target parameterfor (TargetClass target : targets) {String fieldVal = target.targetField;}
// Instance methods from inner class in do-while (3 instances)MemberInner inner = new MemberInner();int count = 0;do {TargetClass t1 = inner.instanceMethod1(targets);TargetClass t2 = inner.instanceMethod2(targets[0]);TargetClass t3 = inner.instanceMethod3();count++;} while (count < 3);
// Switch statementswitch (targets.length) {case 0:throw new IOException("No targets provided");case 1:variableCall();break;default:break;}
// Type declaration statementTargetClass.StaticNested nestedType;
// Expression statementint expr = targets.length * 2;
// Annotations (2 instances)@Annotation1TargetClass annotated1 = new TargetClass();@Annotation2TargetClass annotated2 = annotated1;
// this(arguments) - constructor chaining in local classclass SubTarget extends TargetClass {SubTarget() {this("default");}SubTarget(String val) {super(val);}}
variableCall();
// Uses outer thisSourceClass outerThis = SourceClass.this;
return targets.length > 0 ? targets[0] : new TargetClass();}
private void variableCall() {}
class MemberInner {protected TargetClass instanceMethod1(TargetClass[] targets) {return new TargetClass();}protected TargetClass instanceMethod2(TargetClass target) {return target;}protected TargetClass instanceMethod3() {return new TargetClass();}}}
final class TargetClass {String targetField;
TargetClass() {}TargetClass(String val) {this.targetField = val;}
static class StaticNested {}}
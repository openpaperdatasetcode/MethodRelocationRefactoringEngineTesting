package test.refactoring;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
class SourceClass<T> {public static class StaticNested {public void useSourceMethod(SourceClass<String> source, TargetClass target) {source.processTarget(target);}}
void createLocalInner() {class LocalInner {void invokeProcess(TargetClass target) {processTarget(target);}}new LocalInner().invokeProcess(new TargetClass());}
@RefactorTestAnnotationpublic void processTarget(TargetClass target) {variableCall(target);TargetClass.TargetInner inner = target.new TargetInner();
for (int i = 0; i < 2; i++) {TargetClass result = inner.recursiveCall(target, 2);}}
private void variableCall(TargetClass target) {target.setData("processed_data");}}
protected class TargetClass {private String data;
public void setData(String data) {this.data = data;}
public String getData() {return data;}
class TargetInner {public TargetClass recursiveCall(TargetClass target, int depth) {if (depth <= 0) {return super.getClass().cast(target);}return recursiveCall(target, depth - 1);}}}
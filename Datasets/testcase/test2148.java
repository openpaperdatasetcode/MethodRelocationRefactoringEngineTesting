package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
interface SourceInterface {}
private class SourceClass implements SourceInterface {static class StaticNested {}
class MemberInner {class InnerRecursive {@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
@MethodAnnotationprivate abstract Object methodToMove(TargetClass<String> target);
// Concrete method demonstrating featuresvoid demoFeatures(TargetClass<String> target) {TargetClass<String>.LocalInnerCreator creator = target.new LocalInnerCreator();
int i = 0;while (i < 5) {if (creator.field == null) { // NullLiteral expressioncontinue;}creator.variableCall();i++;}
try {Method method = TargetClass.LocalInnerCreator.class.getMethod("variableCall");method.invoke(creator);} catch (Exception e) {}}}}
void createLocalInner() {class LocalInner {}}}
class TargetClass<T> {class LocalInnerCreator {T field;
void variableCall() {class LocalInner {}}}}
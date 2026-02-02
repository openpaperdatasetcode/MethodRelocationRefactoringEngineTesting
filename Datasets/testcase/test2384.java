package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
sealed class SourceClass<T> extends ParentSource<T> permits SourceSubClass {class SourceInner {private int methodToMove(TargetClass<String>.TargetStaticNested.TargetInnerRec targetRec) {// Method parameter is Target classTargetClass<String> target = targetRec.outer;
// Instance method in ternary operatorSourceInner inner = new SourceInner();List<String> list = (targetRec.count > 3)? inner.innerMethod(3): inner::defaultMethod;
// Switch statementswitch (targetRec.status) {case "ACTIVE":targetRec.count++;break;default:targetRec.count = 0;}
// Type declaration statementclass TargetProcessor {int process(TargetClass<String>.TargetStaticNested.TargetInnerRec rec) {return rec.count * 2;}}TargetProcessor processor = new TargetProcessor();
// Variable callString var = targetRec.data;int countVar = targetRec.count;targetRec.outer.process();
// With boundsSourceClass<? extends Number> boundedSource = new SourceSubClass<>();
// Used by reflectiontry {Method method = targetRec.getClass().getMethod("increment");method.invoke(targetRec);} catch (Exception e) {// No new exception thrown}
return processor.process(targetRec);}
public List<String> innerMethod(int num) {return new ArrayList<>();}
public List<String> defaultMethod() {return new ArrayList<>();}}
{new Runnable() {}; // Anonymous inner class}}
final class SourceSubClass<T> extends SourceClass<T> {}
class ParentSource<T> {}
abstract class TargetClass {
static class TargetStaticNested {
class TargetInnerRec {
U data;
int count;
String status;
TargetClass outer = TargetClass.this;
void increment() {count++;}}}
void process() {}}
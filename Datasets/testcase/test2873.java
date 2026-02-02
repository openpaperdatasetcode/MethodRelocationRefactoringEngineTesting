import java.util.ArrayList;import java.util.List;
non-sealed abstract class SourceClass {static class SourceStaticNested {}
class SourceInner {class InnerRec {public List<String> methodToMove(TargetClass target) {synchronized (target) {OtherClass other = new OtherClass();String[] initArray = {other.getAccessor1().m1().m2().m3(),other.getAccessor2().m1().m2().m3()};
TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.useField(target.targetField);
List<String> result = new ArrayList<>();for (String s : initArray) {result.add(s);}return result;}}
public List<String> methodToMove(TargetClass target, int param) {return methodToMove(target);}}}
public void outerMethod() {class LocalInner {void invokeInnerRec(TargetClass target) {new SourceInner().new InnerRec().methodToMove(target);}}new LocalInner().invokeInnerRec(new TargetClass() {});}}
protected abstract class TargetClass {String targetField;static class TargetStaticNested {void useField(String field) {}}}
class OtherClass {AccessorObj getAccessor1() { return new AccessorObj(); }AccessorObj getAccessor2() { return new AccessorObj(); }}
class AccessorObj {AccessorObj m1() { return this; }AccessorObj m2() { return this; }String m3() { return "chainedResult"; }}
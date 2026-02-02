package test;
import java.util.List;
strictfp class SourceClass {class MemberInner {class InnerRec {private Object moveMethod(TargetClass.Inner.InnerRec target, List<String> param) {class LocalInner {}LocalInner li = new LocalInner();
TargetClass.Inner.InnerRec newInner = new TargetClass().new Inner().new InnerRec();variableCall(target);
return newInner;}
private void variableCall(TargetClass.Inner.InnerRec t) {}}}}
protected class TargetClass {static class StaticNested {}
class Inner {class InnerRec {}}}
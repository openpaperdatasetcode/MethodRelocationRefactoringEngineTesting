package test;
protected class SourceClass {static class StaticNested {}
class MemberInner {}
public int calculate(TargetClass.InnerRec innerRec) {int result = 0;try {if (innerRec == null) {throw new NullPointerException("InnerRec is null");}if (innerRec.value == null) {throw new NullPointerException("InnerRec value is null");}result = innerRec.value;} catch (NullPointerException e) {result = -1;}return result;}}
public class TargetClass {class Inner {class InnerRec {Integer value;
void process() {class LocalInner {}}}}}
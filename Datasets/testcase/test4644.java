package test;
interface ParentInterface {}
interface SourceInterface extends ParentInterface {static class StaticNested {class Inner {class InnerRec {public TargetInterface method() {return null;}}}}
default void sampleMethod() {class LocalInner {}LocalInner local = new LocalInner();}}
protected interface TargetInterface {class MemberInner {TargetInterface targetField;}
TargetInterface method();}
class SourceInnerRecImpl extends SourceInterface.StaticNested.Inner.InnerRec {@Overridepublic TargetInterface method() {TargetInterface.MemberInner inner = new TargetInterface.MemberInner();TargetInterface raw = inner.targetField;return raw;}}
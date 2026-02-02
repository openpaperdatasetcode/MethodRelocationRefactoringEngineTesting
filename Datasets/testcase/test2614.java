package test.same;
class SourceClass {class MemberInner {}
void instanceMethod() {super.toString();TargetClass target = new TargetClass();TargetClass.MemberInner.InnerRec rec = target.new MemberInner().new InnerRec();Object var = rec.targetField;
class LocalInner {}LocalInner local = new LocalInner();
List rawList = new ArrayList();rawList.add(rec);
return this;}}
/**
Javadoc for TargetClass
*/
class TargetClass {
class MemberInner {
record InnerRec() {
Object targetField;
}
}
}
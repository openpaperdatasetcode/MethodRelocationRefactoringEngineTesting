package samepkg;
private enum SourceEnum {INSTANCE;
static class SourceStaticNested {protected int varargsMethod(int... params) {return params[0];}}
class MemberInner {default Object varargsMethod(PublicTargetEnum... targetEnums) {PublicTargetEnum varCall = targetEnums[0];int[] arr = {1};int result = SourceStaticNested::varargsMethod;
return varCall.new TargetInner();}}
void methodWithLocal() {class LocalInner {}new LocalInner();}}
public enum PublicTargetEnum {VALUE1, VALUE2;
class TargetInner {}}
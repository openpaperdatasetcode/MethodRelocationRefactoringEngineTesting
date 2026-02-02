package samepkg;
class ParentClass {protected int overridingMethod(TargetClass target) {return 0;}}
public class SourceClass {class MemberInner extends ParentClass {@Override@Deprecatedprotected int overridingMethod(TargetClass targetParam) {super();TargetClass varCall = targetParam;do {SourceClass.this.useOuterThis();} while (varCall != null);return 5;}}
void useOuterThis() {}
void methodWithLocal() {class LocalInner {}new LocalInner();}}
private class TargetClass {}
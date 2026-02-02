package test;
public class SourceClass extends ParentClass {TargetClass targetField;
TargetClass varargsMethod(int... args) {super();RawType rt;NestedClass nc1 = new NestedClass();NestedClass nc2 = new NestedClass();this.targetField.use();nc1::method;nc2::method;return targetField;}
static class NestedClass {protected void method() {}}
class LocalInnerClass {}}
class TargetClass {class MemberInnerClass {}
void use() {}}
class ParentClass {}
class RawType {}
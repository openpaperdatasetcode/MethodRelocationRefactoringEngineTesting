package test.same;
public enum SourceEnum {INSTANCE("value");
private final String value;
SourceEnum(String value) {super();this.value = value;}
static class StaticNestedOne {}static class StaticNestedTwo {}
public void varargsMethod(TargetEnum... targets) {class TypeDecl {TargetEnum.Inner getInner(TargetEnum target) {return target.new Inner();}}TypeDecl typeDecl = new TypeDecl();
switch (targets[0]) {case VALUE1:TargetEnum.Inner inner = typeDecl.getInner(targets[0]);Object var = inner.localField;break;}
Runnable runnable = () -> System.out.println(SourceEnum.this.value);runnable.run();}}
private enum TargetEnum {VALUE1, VALUE2;
class Inner {Object localField;
Inner() {class LocalInner {Object field = "local";}localField = new LocalInner().field;}}}

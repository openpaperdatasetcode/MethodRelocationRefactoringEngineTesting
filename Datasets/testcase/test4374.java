package source;
protected class SourceClass {static class SourceStaticNested {}class SourceMemberInner {}
@DeprecatedObject moveMethod(TargetClass.TargetInner targetInnerParam) {label: {int count = 0;do {Object result = targetInnerParam.callVarargsMethod(1, "test", 3.0);count++;if (count > 2) break label;} while (count <= 2);}return new Object();}}
// Different package from sourcepackage target;
import source.SourceClass;
class TargetClass {static class TargetStaticNested {}class TargetInner {Object callVarargsMethod(Object... args) {return args.length > 0 ? args[0] : null;}}}
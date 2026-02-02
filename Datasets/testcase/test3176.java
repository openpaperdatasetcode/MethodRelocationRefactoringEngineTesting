enum SourceEnum<T> {INSTANCE;
class MemberInner<T> {record InnerRecord(TargetEnum<T> target) {final TargetEnum<T> process(TargetEnum<T>... targets) {super();TypeDeclaration<T> typeDecl = new TypeDeclaration<>();TargetEnum<T> result = null;
outerLoop:for (TargetEnum<T> target : targets) {// Local inner classclass LocalInner {void processTarget(TargetEnum<T> t) {// Access instance field + variable callt.instanceField = (T) ("data_" + t.name());TargetEnum.StaticNested.process(t.instanceField);}}
new LocalInner().processTarget(target);result = target;
if (target == TargetEnum.VALUE) {break outerLoop;}}
return result;}}}
class TypeDeclaration {}
}
enum TargetEnum<T> {VALUE;
T instanceField;
static class StaticNested {static <T> void process(T data) {}}}
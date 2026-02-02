package same;
class RecordSuperClass {protected RecordSuperClass() {}}
public record Source(int sourceField) extends RecordSuperClass {static class SourceStaticNested {}class SourceMemberInner {public void instanceMethod(Target<String> targetParam) {class LocalType {String typeField = targetParam.data();}new LocalType();
super();
String[] items = {"a", "b", "c"};for (String item : items) {if (item.isEmpty()) {return;}}
Object var = targetParam;String accessField = targetParam.data();}}}
record Target(U data) {
public Target {
class TargetLocalInner {
void useData() {
System.out.println(data);
}
}
new TargetLocalInner().useData();
}
}
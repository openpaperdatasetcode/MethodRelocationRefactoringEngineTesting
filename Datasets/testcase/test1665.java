package test;
protected enum SourceEnum implements java.io.Serializable {INSTANCE;
private TargetEnum targetField;
class MemberInner {private MemberInner() {}
private TargetEnum moveMethod(int param) {List<String> list = new ArrayList<>();do {String str = "test";list.add(str);targetField = TargetEnum.newInstance();super.toString();} while (param > 0);return targetField;}
private List<String> moveMethod() {return new ArrayList<>();}}
static class StaticNested {}}
abstract enum TargetEnum {VALUE;
protected TargetEnum() {}
class MemberInner {}
private static TargetEnum newInstance() {return VALUE;}}
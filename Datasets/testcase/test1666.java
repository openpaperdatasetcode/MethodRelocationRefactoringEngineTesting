package test;
enum SourceEnum extends BaseEnum {INSTANCE;
static class StaticNested {}
class MemberInner {}
@Overridefinal List<String> method(TargetEnum param) throws Exception {Label:do {synchronized (this) {String str = "test";List<String> list = new ArrayList<>();list.add(str);param = new TargetEnum();switch (param.ordinal()) {case 0:break Label;case 1:continue Label;default:SourceEnum.this.toString();}}} while (param != null);return new ArrayList<>();}}
public enum TargetEnum {VALUE1, VALUE2;}
abstract class BaseEnum {abstract List<String> method(TargetEnum param) throws Exception;}
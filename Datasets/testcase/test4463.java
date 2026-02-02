package test;
interface EnumProcessor<T> {TargetEnum.Inner process(TargetEnum.Inner targetInner);}
public enum SourceEnum<T> implements EnumProcessor<T> {VALUE1, VALUE2;
static class StaticNested {}class MemberInner {}
@Overridepublic TargetEnum.Inner process(TargetEnum.Inner targetInner) {;int var = targetInner.innerField;
TargetEnum.StaticNested nested = new TargetEnum.StaticNested();int nestedVal = nested.nestedField;
return processOverload(targetInner, var);}
public TargetEnum.Inner processOverload(TargetEnum.Inner targetInner, int param) {return targetInner;}}
strictfp enum TargetEnum {TARGET1, TARGET2;
static class StaticNested {int nestedField = 1;}
class Inner {int innerField = 2;}}
package test;
interface MyInterface {}
non-sealed enum SourceEnum {ENUM_CONST;
class SourceInner {private TargetEnum methodToMove(TargetEnum.InnerRec innerRec) {TargetEnum.StaticNested nested = new TargetEnum.StaticNested();nested.synchronizedAccessor();
Runnable runnable = TargetEnum::valueOf;runnable.run();
if (innerRec == null) {throw new NullPointerException();}
TargetEnum target = innerRec.target();return target;}}}
abstract enum TargetEnum implements MyInterface {TARGET_CONST;
static class StaticNested {synchronized void synchronizedAccessor() {}}
record InnerRec(TargetEnum target) {}}
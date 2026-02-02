package test;
protected enum TargetEnum extends ParentEnum {VALUE1, VALUE2;int targetField;class TargetInner {}}
abstract class ParentEnum {}
enum SourceEnum {ENUM_VALUE;
static class StaticNested {}
public SourceEnum() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@RefactorAnnotationstrictfp Object methodToMove(TargetEnum.TargetInner param) {class InnerClass {private void process(TargetEnum target) {do {int val = target.targetField;target.targetField = 1;} while (target.targetField < 2);}}
TargetEnum target = TargetEnum.VALUE1;new InnerClass().process(target);super.toString();Object var = target.targetField;
try {genericMethod(target);} finally {}
return var;}
@RefactorAnnotationstrictfp Object methodToMove(TargetEnum.TargetInner param, int extra) {// Overload existsreturn null;}
public <T extends TargetEnum> int genericMethod(T target) {return new StaticNested().hashCode();}}
@interface RefactorAnnotation {}
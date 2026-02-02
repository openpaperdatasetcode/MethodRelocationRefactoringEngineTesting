package test;
sealed class SourceClass permits SourceSubClass {class MemberInner {record SourceInnerRec() {private TargetClass methodToMove(TargetClass.StaticNested.Rec... innerRecs) {TargetClass target = new TargetClass() {{super();}};
for (TargetClass.StaticNested.Rec rec : innerRecs) {int val = rec.field;val += TargetClass.StaticNested.staticField;}
return target;}}}
void createLocalInner() {class LocalInner {}}}
final class SourceSubClass extends SourceClass {}
abstract class TargetClass {static class StaticNested {static int staticField;
record Rec(int val) {int field = val;}}}
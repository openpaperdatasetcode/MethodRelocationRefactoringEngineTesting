package test;
public class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
final int methodToMove(TargetClass.InnerRec innerRec) {super.toString();int total = 0;
for (int i = 0; i < innerRec.count(); i++) {total += innerRec.value();}
return total;}}
final class TargetClass {class MemberInner {}
record InnerRec(int count, int value) {int getField() {return count + value;}}}
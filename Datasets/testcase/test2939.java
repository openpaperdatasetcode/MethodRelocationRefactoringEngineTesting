import java.util.List;import java.util.ArrayList;
private class SourceClass<T> {private int sourceField;static class StaticNested {}class MemberInner {}
/**
Javadoc for methodToMove
@param target instance of TargetClass
@return TargetClass instance
@throws Exception if error occurs*/private TargetClass methodToMove(TargetClass target) throws Exception {private class LocalType {void useField() {this.field = SourceClass.this.sourceField;}int field;}LocalType local = new LocalType();local.useField();
TargetClass.StaticNested nested = new TargetClass.StaticNested();if (target.targetField > 0) {return target;}breakLabel:for (int i = 0; i < 5; i++) {if (i == 3) break breakLabel;}SourceClass.this.sourceField = target.targetField;target.instanceMethod();return new TargetClass();}}
public class TargetClass {int targetField;static class StaticNested {}
void instanceMethod() {}}
class ParentClass {private List<String> callMethod() throws Exception {SourceClass<String> source = new SourceClass<>();TargetClass target = new TargetClass();throw new Exception(source.methodToMove(target));List<String> list = new ArrayList<>();list.add(target::toString);return list;}}
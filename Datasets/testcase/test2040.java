package test;
import java.util.List;import java.util.ArrayList;
protected enum SourceEnum {VALUE1, VALUE2;
static class StaticNested {}class MemberInner {}
protected List<String> methodToMove() {List<String> list = new ArrayList<>();int field = TargetEnum.targetField;
new StaticNested();new MemberInner();
super.toString();TargetEnum enumVal = TargetEnum.VALUE3;
try {ForLoop:for (int i = 0; i < field; i++) {list.add(String.valueOf(i));if (i == 5) {break ForLoop;}}} finally {list.add("finally");}
enumVal.new AnonymousInner() {{System.out.println(enumVal.name());}};
return list;}}
public enum TargetEnum {VALUE3, VALUE4;
static int targetField = 10;
class AnonymousInner {}}
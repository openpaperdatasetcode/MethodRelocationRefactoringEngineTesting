package test;
import java.util.ArrayList;import java.util.List;
// Private source enum with member inner class and static nested classprivate enum SourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {// Protected static method (position: source_inner)protected static List<String> process(TargetEnum target) {List<String> result = new ArrayList<>();labeledBlock: {// Uses outer this (SourceEnum instance)result.add(SourceEnum.this.name());
// Expression statementtarget.setField("processed");variableCall(target);
if (target.getField() == null) break labeledBlock;result.add(target.getField());}
new StaticNested();return result;}
private static void variableCall(TargetEnum target) {target.staticNested.doTask();}}}
// Sealed target enum with static nested classsealed enum TargetEnum permits TargetEnum.VALUE1, TargetEnum.VALUE2 {VALUE1, VALUE2;
private String field;
public static class StaticNested {public void doTask() {}}
public static StaticNested staticNested = new StaticNested();
public String getField() {return field;}
public void setField(String field) {this.field = field;}
protected static List<String> process() {return new ArrayList<>();}}
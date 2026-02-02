package test;
import java.util.List;import java.util.ArrayList;
protected abstract class SourceClass extends ParentClass {private String outerPrivateField = "privateData";
public static class SourceStaticNested {}
protected List<String> recursiveMethod(TargetClass target, int depth) {List<String> result = new ArrayList<>();
if (depth <= 0) {List<String> othersResult = new OthersClass().getOthersList();result.addAll(othersResult);return result;}
protected String var1 = target.staticNested.field1;protected int var2 = depth;protected TargetClass.TargetStaticNested var3 = target.staticNested;
result.add(target.field);result.add(outerPrivateField);
new Runnable() {@Overridepublic void run() {result.add(var1);}};
;
result.addAll(this.recursiveMethod(target, depth - 1));
return result;}}
abstract class ParentClass {}
abstract class TargetClass {public String field;public TargetStaticNested staticNested = new TargetStaticNested();
public static class TargetStaticNested {public String field1 = "nestedData";}}
class OthersClass {default List<String> getOthersList() {return List.of("othersData");}}
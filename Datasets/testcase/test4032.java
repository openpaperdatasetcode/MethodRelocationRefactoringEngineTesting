package same.pkg;
import java.util.ArrayList;import java.util.List;
/**
Abstract target class with static nested class for data handling*/private abstract class TargetClass {protected String targetField1;protected int targetField2;
public static class TargetInner {public List<String> collectData(TargetClass target) {List<String> data = new ArrayList<>();data.add(target.targetField1);data.add(String.valueOf(target.targetField2));return data;}}
public abstract String getBaseData();}
abstract class SourceClass {static class StaticNested {static Object recursiveHelper(SourceClass source, int depth) {if (depth <= 0) {return new ArrayList<>();}return source.processTarget(new TargetClass() {@Overridepublic String getBaseData() {return "BaseData-" + depth;}}, depth - 1);}}
public List<String> processTarget(TargetClass targetParam, int depth) {TargetClass.TargetInner targetInner = new TargetClass.TargetInner();List<String> result = targetInner.collectData(targetParam);
String field1Val = targetParam.targetField1;int field2Val = targetParam.targetField2;targetParam.targetField1 = "Updated-" + field1Val;targetParam.targetField2 = field2Val + 1;
Object recursiveResult = StaticNested.recursiveHelper(this, depth);if (recursiveResult instanceof List) {result.addAll((List<String>) recursiveResult);}
class LocalInner {void appendSourceInfo() {result.add(SourceClass.this.getClass().getSimpleName());}}new LocalInner().appendSourceInfo();
return result;}
@Overridepublic abstract List<String> processTarget(TargetClass targetParam, int depth);}
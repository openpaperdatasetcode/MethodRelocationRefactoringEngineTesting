package test;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

interface DataProcessor {
String process(String data);
}

abstract class BaseRecord {
protected abstract String getBaseData();
}

// Source: default record class with extends, implements, static nested, anonymous inner class
record SourceRecord(String id, String value) extends BaseRecord implements DataProcessor {
// Static nested class
static class SourceStaticNested {
public static String format(String s) {
return "formatted:" + s;
}
}

// Inner class (source_inner)
public class SourceInner {
// Source contains target's field (per_condition)
private TargetRecord.TargetInnerRec targetField;

// Private recursive method to be refactored
private List<String> recursiveMethod(TargetRecord.TargetInnerRec target, int depth) {
List<String> result = new ArrayList<>();

// Variable call
targetField = target;
result.add(target.getRecData());

// Numbers: "3" + ClassInstanceCreation
if (depth <= 3) {
TargetRecord newTarget = new TargetRecord("t" + depth, "val" + depth);
result.add(newTarget.value());
}

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Recursion depth: " + depth);
}
};
anonTask.run();

// Recursion base case
if (depth <= 0) {
return result;
}

// Recursive invocation
result.addAll(recursiveMethod(target.createNext(depth - 1), depth - 1));
return result;
}
}

@Override
public String process(String data) {
return id + ":" + data;
}

@Override
protected String getBaseData() {
return value;
}
}

// Target: public record class with implements, member inner class
public record TargetRecord(String id, String value) implements Serializable {
// Member inner class (target_inner_rec)
public class TargetInnerRec {
private String recData;
private int level;

public TargetInnerRec(String data, int level) {
this.recData = data;
this.level = level;
}

public String getRecData() {
return recData;
}

public TargetInnerRec createNext(int newLevel) {
return new TargetInnerRec(recData + "_next", newLevel);
}
}

public TargetInnerRec createInner(String data, int level) {
return new TargetInnerRec(data, level);
}
}
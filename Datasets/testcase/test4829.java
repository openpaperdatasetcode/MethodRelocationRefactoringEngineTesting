package test.refactoring;
interface Action {
void execute();
}

abstract record ParentTargetRecord(String baseData) {
public abstract String getProcessedData();
}

public record SourceClass(String sourceField) {
public class SourceInner {
private int innerCounter = 0;

private SourceInner(TargetClass targetParam) {
if (targetParam == null) {
throw new NullPointerException("TargetClass parameter cannot be null");
}

assert targetParam.baseData() != null : "Target's baseData must not be null";

Action anon1 = new Action() {
@Override
public void execute() {
innerCounter++;
variableCall(targetParam, "Anon1 executed");
}
};

Action anon2 = new Action() {
@Override
public void execute() {
innerCounter--;
variableCall(targetParam, "Anon2 executed");
}
};

switch (targetParam.baseData().length()) {
case 3:
anon1.execute();
break;
case 5:
anon2.execute();
break;
default:
anon1.execute();
anon2.execute();
}

if (innerCounter < 0) {
return;
}
}

private void variableCall(TargetClass target, String message) {
System.out.printf("%s | Target data: %s | Counter: %d%n",
message, target.getProcessedData(), innerCounter);
}

public SourceInner createInstance(TargetClass target) {
return new SourceInner(target);
}
}
}

private record TargetClass(String baseData) extends ParentTargetRecord {
public TargetClass {
class LocalInnerClass {
String formatData(String data) {
return "Formatted: " + data;
}
}

LocalInnerClass local = new LocalInnerClass();
this.baseData = local.formatData(baseData);
}

@Override
public String getProcessedData() {
return baseData().toUpperCase();
}
}
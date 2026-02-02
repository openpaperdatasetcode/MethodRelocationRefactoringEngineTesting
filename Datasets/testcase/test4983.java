package refactoring.test;

public class SourceClass {
class MemberInner {
String innerField;

String getInnerValue() {
return innerField;
}
}

strictfp Object varargsMethod(TargetClass targetParam, Object... args) {
type declaration statement;
MemberInner memberInner = new MemberInner();
variable call = targetParam.targetField;

class LocalInner {
Object processArgs() {
return args.length > 0 ? args[0] : null;
}
}

LocalInner localInner = new LocalInner();
Object argResult = localInner.processArgs();

int i = 0;
while (i < args.length) {
String callResult = targetParam.new FinalInner().overrideMethod(memberInner.getInnerValue());
i++;
}

return argResult;
}

strictfp Object varargsMethod(TargetClass targetParam, String singleArg) {
return targetParam.targetField;
}
}

class TargetClass implements SomeInterface {
String targetField = "targetValue";

final class FinalInner {
@Override
public String overrideMethod(String input) {
class LocalInner {
String modifyInput() {
return input + targetField;
}
}
return new LocalInner().modifyInput();
}
}
}

interface SomeInterface {
String overrideMethod(String input);
}
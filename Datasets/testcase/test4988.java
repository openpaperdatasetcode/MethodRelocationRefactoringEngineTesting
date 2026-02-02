package refactoring.test;

strictfp class SourceClass {
class MemberInner {
TargetClass getTarget() {
return new TargetClass();
}
}

final TargetClass recursiveMethod(TargetClass targetParam, int depth) {
if (depth <= 0) {
return targetParam;
}

class LocalInner {
int process() {
variable call = targetParam.value;
return call;
}
}

LocalInner local = new LocalInner();
int result = 0;
do {
result = new SubClass().subMethod(targetParam, local);
depth--;
} while (depth > 0);

; // Empty statement

return recursiveMethod(targetParam, depth);
}
}

public class TargetClass {
int value = 5;

void targetOperation() {
class LocalInner {
int getValue() {
return value;
}
}
}
}

class SubClass extends SourceClass {
int subMethod(TargetClass target, SourceClass.LocalInner local) {
return target.new LocalInner().getValue() + local.process();
}
}
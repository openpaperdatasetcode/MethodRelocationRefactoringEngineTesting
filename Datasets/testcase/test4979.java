package refactoring.test;

protected enum SourceEnum {
INSTANCE;

protected String outerProtectedField = "source_protected_field";
private TargetEnum target = TargetEnum.VALUE1;

class MemberInner {
void processTarget() {
variable call = target.targetField;
expression statement = call + outerProtectedField;
}
}

@Deprecated
public abstract void abstractMethod();

@Override
public void abstractMethod() {
super();
type declaration statement;
MemberInner inner = new MemberInner();

try {
Runnable anon = new Runnable() {
@Override
public void run() {
inner.processTarget();
RawType raw = new RawType();
}
};
anon.run();
} catch (Exception e) {}

TargetEnum callResult = (target == TargetEnum.VALUE1) ?
SourceEnum.callVarargs("arg1") :
SourceEnum.callVarargs("arg1", "arg2");
}

default TargetEnum callVarargs(String... params) {
return TargetEnum.VALUE2;
}
}

public enum TargetEnum {
VALUE1, VALUE2;

String targetField = "target_enum_field";
}

class RawType {}
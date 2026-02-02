package test;
enum SourceEnum {INSTANCE;
class FirstSourceMemberInner {}class SecondSourceMemberInner {}
private TargetEnum<String> targetField = TargetEnum.VALUE;
strictfp Object instanceMethod() {class LocalDeclaredType {}LocalDeclaredType localType = new LocalDeclaredType();
FirstSourceMemberInner inner1 = new FirstSourceMemberInner();SecondSourceMemberInner inner2 = new SecondSourceMemberInner();
int count = 0;while (count < 3) {TargetEnum<String>.TargetInnerRec innerRec = targetField.new TargetInnerRec();innerRec.doAction(); // Variable callcount++;}
return localType;}}
protected enum TargetEnum<T> {VALUE;
record TargetInnerRec() {void doAction() {new Runnable() {@Overridepublic void run() {}};}}}
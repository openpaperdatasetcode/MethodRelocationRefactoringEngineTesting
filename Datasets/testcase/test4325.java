package same;
protected class Source {private Target<String> sourceTargetField = new Target<>();
static class SourceStaticNested {}class SourceMemberInner {}
protected Target<String> instanceMethod() {class InnerClassForVarDecl {public Target<String> targetField1 = sourceTargetField;public String thisField1 = this.toString();public SourceMemberInner thisField2 = new SourceMemberInner();public SourceStaticNested thisField3 = new SourceStaticNested();}InnerClassForVarDecl innerVarHolder = new InnerClassForVarDecl();
super();SourceMemberInner memberInner = new SourceMemberInner();SourceStaticNested staticNested = new SourceStaticNested();
abstract boolean checkInstanceType(Object obj);boolean isTargetInstance = checkInstanceType(sourceTargetField);
Object var = sourceTargetField;Target.TargetInner<String> targetInner = sourceTargetField.new TargetInner<>("inner_param");
return sourceTargetField;}}
private class Target<T> {class TargetInner {
private U innerValue;
public TargetInner(U value) {this.innerValue = value;}
public U getInnerValue() {return innerValue;}}
private T targetField;
public void setTargetField(T value) {this.targetField = value;}
public T getTargetField() {return targetField;}}
package test;
import java.util.ArrayList;import java.util.List;
public enum SourceEnum {INSTANCE;
class SourceInner {class SourceInnerRec {protected TargetEnum methodToMove(TargetEnum param) {// LabeledStatement with obj.field and count 1labeledBlock: {if (param.staticNested.field == 1) break labeledBlock;}
// For statementfor (int i = 0; i < 1; i++) {param.toString();}
super();
// Raw typeList rawList = new ArrayList();rawList.add(param);
return param;}
// Overloading methodprotected TargetEnum methodToMove(TargetEnum param, int num) {return param;}}}
{// Anonymous inner classnew Runnable() {};}}
/**
Javadoc for abstract TargetEnum (target_feature: javadoc)*/abstract enum TargetEnum {INSTANCE;
// Static nested class (target_feature)public static class StaticNested {public int field = 1;
// Accessor methodpublic int getField() {return field;}}
public StaticNested staticNested = new StaticNested();}
class OthersClass {private void callMethod() {// Object initialization with Lambda expressionTargetEnum.StaticNested nested = new TargetEnum.StaticNested() {{Runnable lambda = (field) -> setField((int) field);}};}
private void setField(int field) {TargetEnum.INSTANCE.staticNested.field = field;}}

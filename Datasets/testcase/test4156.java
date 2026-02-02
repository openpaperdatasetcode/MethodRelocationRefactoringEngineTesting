package test;
class ParentClass {protected void initInner (TargetClass target, String data) {TargetClass.TargetInner inner = target.new TargetInner ();inner.setInnerData (data);}}
protected class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass ();
protected TargetClass instanceMethod() {super.toString();
private void privateForLoop () {for (int i = 0; i < 1; i++) {if (i == 0) continue;System.out.println (TargetClass.STATIC_FIELD);}}privateForLoop ();
for (int j = 0; j < 1; j++) {TargetClass.TargetInner inner = targetField.new TargetInner ();inner.getInnerData ();}
TargetClass newTarget = new TargetClass (this::initInner);
TargetClass varCall = targetField;return varCall;}}
public class TargetClass implements DataHolder {public static final String STATIC_FIELD = "targetStaticField";private String data;
public class TargetInner {private String innerData;
public void setInnerData(String data) {this.innerData = data;}
public String getInnerData() {return innerData;}}
public TargetClass (InitCallback callback) {callback.init (this, "initData");}
@Overridepublic void setData(String data) {this.data = data;}
@Overridepublic String getData() {return data;}
@FunctionalInterfacepublic interface InitCallback {void init (TargetClass target, String data);}}
interface DataHolder {void setData (String data);String getData ();}
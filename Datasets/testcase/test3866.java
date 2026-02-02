package test.refactoring;
abstract class EnumParent {}
interface DataProcessor {Object process (Object data);}
enum SourceEnum extends EnumParent {INSTANCE1, INSTANCE2;
private String outerPrivate = "source_private_data";
private TargetEnum targetField = TargetEnum.VALUE1;
static class SourceStaticNested {
abstract static class AbstractFieldHolder {protected Object field1;protected Object field2;
abstract void setFields(Object f1, Object f2);}
synchronized Object recursiveMethod (TargetEnum.TargetInner targetInner, int depth) {if (depth <= 0) {return targetInner.getInnerData ();}
AbstractFieldHolder holder = new AbstractFieldHolder () {@Overridevoid setFields (Object f1, Object f2) {this.field1 = f1;}};
TargetEnum.TargetInner newInner = targetField.new TargetInner ("recursion_" + depth);
if (newInner == null) {throw new NullPointerException ("TargetInner instance is null");}
variableCall (newInner, outerPrivate); 
holder.setFields (newInner.getInnerData (), depth);
return recursiveMethod (newInner, depth - 1);}
private void variableCall (TargetEnum.TargetInner inner, String data) {inner.setInnerData (inner.getInnerData () + "_" + data);}}
private DataProcessor anonProcessor = new DataProcessor () {@Overridepublic Object process (Object data) {SourceStaticNested nested = new SourceStaticNested ();
return nested.recursiveMethod (targetField.new TargetInner (data.toString ()), 2);}};
public Object processTarget () {return anonProcessor.process (outerPrivate);}}
private enum TargetEnum {VALUE1, VALUE2;
class TargetInner {private Object innerData;
public TargetInner (Object data) {this.innerData = data;}
public DataProcessor getAnonProcessor () {return new DataProcessor () {@Overridepublic Object process (Object data) {return TargetInner.this.innerData + "_anon_processed";}};}
// Getter/Setterpublic Object getInnerData() {return innerData;}
public void setInnerData(Object innerData) {this.innerData = innerData;}}}
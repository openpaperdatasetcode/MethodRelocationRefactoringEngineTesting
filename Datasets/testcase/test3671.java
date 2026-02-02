package test;
interface EnumProcessable {int process(TargetEnum target);}
strictfp enum SourceEnum implements EnumProcessable {INSTANCE;
private int privateField = 5;
public class InnerSource {@Overrideprotected int process(TargetEnum target) {class LocalInner {private Object instanceMethod(TargetEnum.InnerTarget inner) {synchronized (inner) {if (inner.field == null) {throw new NullPointerException("InnerTarget field is null");}inner.field = inner.field + SourceEnum.this.privateField;return inner.field;}}}
TargetEnum.InnerTarget inner = new TargetEnum.InnerTarget(3);LocalInner local = new LocalInner();
Runnable lambda = () -> {try {Object result = local.instanceMethod(inner);System.out.println(result);} catch (Exception e) {e.printStackTrace();}};lambda.run();
new Runnable() {@Overridepublic void run() {SourceEnum.this.privateField += inner.getField();}}.run();
return inner.getField() + SourceEnum.this.privateField;}}}
public enum TargetEnum {VALUE1, VALUE2;
public class InnerTarget {public int field;
public InnerTarget(int field) {super();this.field = field;}
public int getField() {return field;}
public void setField(int field) {this.field = field;}}}
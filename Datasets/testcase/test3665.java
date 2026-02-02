package test;
class ParentSourceClass {protected String parentField = "parent_data";}
public class SourceClass extends ParentSourceClass {public class InnerSource {public TargetClass.StaticNestedTarget getNestedTarget() {return new TargetClass.StaticNestedTarget();}}
protected TargetClass instanceMethod(TargetClass target) {class LocalInner {TargetClass process(TargetClass t) {TargetClass.StaticNestedTarget nested = t.new StaticNestedTarget();nested.setValue(SourceClass.this.parentField);return t;}}
LocalInner local = new LocalInner();TargetClass result = local.process(target);
switch (target.getStatus()) {case 1:result.setStatus(2);break;default:result.setStatus(0);break;}
TargetClass.StaticNestedTarget nestedTarget = new InnerSource().getNestedTarget();result.setValue(nestedTarget.getValue());
return result;}}
private class TargetClass {private int status;private String value;
public static class StaticNestedTarget {private String nestedValue;
public void setValue(String val) {this.nestedValue = val;}
public String getValue() {return nestedValue;}}
public int getStatus() {return status;}
public void setStatus(int status) {this.status = status;}
public void setValue(String value) {this.value = value;}
public String getValue() {return value;}}
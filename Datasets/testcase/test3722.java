import java.util.Objects;
strictfp class SourceClass {private TargetClass targetField;
private TargetClass instanceMethod(TargetClass target, int type) {if (target == null) {target = new TargetClass();}
String[] arr1 = {"init_data1", "init_data2"};int[] arr2 = {10, 20};
expressionStatement: {target.memberInner.setData(arr1[type % 2]);target.memberInner.setCount(arr2[type % 2]);}
switch (type) {case 1:variableCall(target);break;case 2:target.memberInner.updateData(TargetClass.StaticField.STATIC_VAL);break;default:break;}
new Runnable() {@Overridepublic void run() {System.out.println("First anonymous: " + target.memberInner.getData());}}.run();
new Runnable() {@Overridepublic void run() {System.out.println("Second anonymous: " + target.memberInner.getCount());}}.run();
return target;}
private void variableCall(TargetClass target) {target.memberInner.updateData("var_call_" + TargetClass.StaticField.STATIC_VAL);}
public void init() {targetField = instanceMethod(new TargetClass(), 1);}}
protected class TargetClass {public static class StaticField {public static final String STATIC_VAL = "target_static";}
public MemberInner memberInner = new MemberInner();
public class MemberInner {private String data;private int count;
public void setData(String data) {this.data = data;}
public void setCount(int count) {this.count = count;}
public void updateData(String newData) {this.data = newData;}
public String getData() {return data;}
public int getCount() {return count;}}}
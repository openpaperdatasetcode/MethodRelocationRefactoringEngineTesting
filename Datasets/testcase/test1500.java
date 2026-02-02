package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Access {}
abstract class Target {public String field1 = "target_field1";public int field2 = 100;
abstract class MemberInner {abstract class InnerRec {String recData;
InnerRec(String data) {this.recData = data;}}}}
class Source {class MemberInner {List<String> dataList = new ArrayList<>();}
@Accessprotected List<String> getTargetData() {// Type declaration statementTarget target = new Target() {@Overrideclass MemberInner {@Overrideclass InnerRec extends Target.MemberInner.InnerRec {InnerRec(String data) {super(data);}}}};
// ConstructorInvocation with 2 target class fieldsTarget.MemberInner.InnerRec rec = target.new MemberInner().new InnerRec(Target.field1 + "_" + Target.field2);
MemberInner sourceInner = new MemberInner();try {// Access instance fieldsourceInner.dataList.add(rec.recData);// Expression statementSystem.out.println("Added: " + rec.recData);} catch (NullPointerException e) {// Throw statementthrow new IllegalArgumentException("Invalid record data", e);}
return sourceInner.dataList;}}
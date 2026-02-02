package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public sealed record SourceRecord(String data)extends ParentRecord permits SourceSubRecord {
class MemberInner {}
{overloadedMethod(3);}
/**
Javadoc for overridden method
@return TargetClass instance*/@Override@MyAnnotationpublic TargetRecord methodToMove() {TargetRecord target = new TargetRecord(5) {{int val = callMethod(3);}};TargetRecord.InnerRec innerRec = target.new InnerRec();int field = innerRec.targetField;
do {for (int num : new int[]{3}) {if (num == field) {break;};}} while (field-- > 0);
overloadedMethod(target);return target;}
public List<String> overloadedMethod(int num) {this.overloadedMethod(new TargetRecord(3));return new ArrayList<>();}
public List<String> overloadedMethod(TargetRecord target) {return new ArrayList<>();}
public void localInnerMethod() {class LocalInner {}}}
final record SourceSubRecord(String data) extends SourceRecord {public SourceSubRecord(String data) {super(data);}}
abstract record ParentRecord() {public abstract TargetRecord methodToMove();}
record TargetRecord(int value) {int targetField = value;
record InnerRec() {int targetField = TargetRecord.this.targetField;}
{new Runnable() {public void run() {}};}
protected int callMethod(int param) {this.callMethod(param);return param;}}
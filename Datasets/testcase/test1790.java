package test;
import java.util.ArrayList;
abstract class BaseRecord {}
sealed record TargetRecord(int value) permits {}
record SourceRecord() extends BaseRecord {public class InnerRecord {@MyAnnotationTargetRecord varargsMethod(TargetRecord... targets) {ArrayList list = new ArrayList();int i = 0;while (i < targets.length) {if (targets[i] == null) {i++;continue;}list.add(targets[i].value());i++;}Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(SourceRecord.this.toString());}};r.run();return new TargetRecord(0) {};}
TargetRecord varargsMethod(String s) {return new TargetRecord(Integer.parseInt(s));}}}
@interface MyAnnotation {}

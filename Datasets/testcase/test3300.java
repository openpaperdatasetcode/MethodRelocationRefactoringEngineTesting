package test;
strictfp record SourceRecord(String data) {class MemberInner {public void callMethod(TargetRecord target) {if (target.value() != null) {SourceRecord.MemberInner.accessData(target);} else {SourceRecord.MemberInner.printDefault();}}
public static void accessData(TargetRecord target) {System.out.println(target.value());}
public static void printDefault() {System.out.println("Default");}}
protected Object moveMethod(TargetRecord target) {class LocalInner {}LocalInner local = new LocalInner();
String processed = super.toString() + target.value();new MemberInner().callMethod(target);return processed;}}
record TargetRecord(String value) {{class TargetLocalInner {String getValue() {return value();}}new TargetLocalInner();}}
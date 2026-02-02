package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Log {}
/**
Target class with member inner class
Provides data processing functionality*/public class Target {private String baseData = "base";
/**
Member inner class for nested data handling/
public class MemberInner {
/*
Inner record class for data storage*/public class InnerRec {private int value;
public InnerRec(int value) {this.value = value;}
public int getValue() {return value;}
public void setValue(int value) {this.value = value;}}
public InnerRec createRec(int val) {return new InnerRec(val);}}}
class SubTarget extends Target {@Overridepublic MemberInner createInner() {return new MemberInner();}
strictfp int calculate(int a, int b) {return a + b;}}
public class Source {private String outerField = "outer";
@Logpublic Object process() throws Exception {Target target = new Target();Target.MemberInner inner = target.new MemberInner();Target.MemberInner.InnerRec rec = inner.createRec(10);
// Raw type usageList rawList = new ArrayList();rawList.add(rec);
// Local inner classclass LocalProcessor {void updateRec(Target.MemberInner.InnerRec r) {r.setValue(r.getValue() + 5);}}
// Anonymous inner classRunnable anonTask = new Runnable() {@Overridepublic void run() {System.out.println(Source.this.outerField); // Uses outer this}};anonTask.run();
// Overloaded methodprocess("overload");
int count = 0;do {new LocalProcessor().updateRec(rec);count++;} while (count < 3);
// Static method call in do-whiledo {Source.printRec(Source.this.new Helper().getRec(rec));} while (count < 4);
// Try statement with throwstry {int result = super.hashCode(); // Super keywordObject[] data = {new SubTarget().calculate(rec.getValue(), 20) // Subclass overriding method in array initialization};return data;} catch (NullPointerException e) {throw new Exception("Processing failed", e);}}
public void process(String input) {// Overloaded method implementation}
private class Helper {Target.MemberInner.InnerRec getRec(Target.MemberInner.InnerRec original) {return original;}}
private static final void printRec(Target.MemberInner.InnerRec r) {System.out.println(r.getValue());}}
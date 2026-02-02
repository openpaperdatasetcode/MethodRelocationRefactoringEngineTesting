package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
class SourceClass {private void log(String msg) {System.out.println(msg);}
private Object processTarget(TargetClass.TargetInnerRec targetRec) {List<String> dataList = new ArrayList<>();dataList.add(targetRec.innerField);
dataList.forEach(this::log);
Object lock = new Object();synchronized (lock) {String var = targetRec.innerField;dataList.add("Processed: " + var);}
try {Method innerMethod = TargetClass.TargetInnerRec.class.getMethod("getInnerField");String reflectedValue = (String) innerMethod.invoke(targetRec);dataList.add("Reflected: " + reflectedValue);} catch (Exception e) {dataList.add("Reflection failed");}
return dataList;}}
class TargetClass {public record TargetInnerRec(String innerField) {public String getInnerField() {return innerField;}}
public class TargetMemberInner {public void useRec(TargetInnerRec rec) {System.out.println(rec.innerField());}}}
package test;
import other.OthersClass;import java.util.ArrayList;import java.util.List;
strictfp class SourceClass {public class MemberInner1 {public class MemberInner2 {public TargetClass.Inner.Rec getRec() {return new TargetClass().new Inner().new Rec();}}}
protected Object getValue(TargetClass.Inner.Rec targetRec) {MemberInner1.Inner2 inner = new MemberInner1().new MemberInner2();List<Object> results = new ArrayList<>();
// Static private method from others_class in constructor parameter list (2 variants)TargetClass temp1 = new TargetClass(OthersClass.process(targetRec.data));TargetClass temp2 = new TargetClass(OthersClass.process(targetRec, 10));
// Switch caseswitch (targetRec.type) {case 1:results.add(targetRec.data);break;case 2:results.add(targetRec.data.toUpperCase());break;default:results.add("default");}
// Variable call - access target inner rec's fieldresults.add(targetRec.data);
// Depends on static fieldresults.add(TargetClass.StaticField + "_" + targetRec.data);
// Requires try-catchtry {if (targetRec.data == null) {throw new NullPointerException("Data is null");}} catch (NullPointerException e) {results.add("Error: " + e.getMessage());}
return results;}}
protected class TargetClass {public static final String StaticField = "static_val";
public TargetClass(Object initData) {}
public class Inner {public class Rec {public String data;public int type;
public Rec() {// Local inner class in targetclass DataInitializer {void init() {data = "initial";type = 1;}}new DataInitializer().init();}}}}
package other;
import test.TargetClass;
public class OthersClass {// Static method variant 1private static Object process(String data) {return "processed: " + data;}
// Static method variant 2private static Object process(TargetClass.Inner.Rec rec, int factor) {return rec.data + "_" + factor;}}
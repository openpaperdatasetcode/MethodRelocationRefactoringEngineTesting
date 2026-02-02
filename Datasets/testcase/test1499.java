package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
class SuperTarget {protected String superField = "base_data";}
public abstract class Target extends SuperTarget {abstract class MemberInner {abstract class InnerRec {String data;
InnerRec(String data) {this.data = data;}
String getData() {return data;}
List<String> fetch() {return List.of(data);}}}}
protected abstract class Source {protected int outerProtected = 10;
static class StaticNested {static int count = 0;}
public int generateId() {return StaticNested.count++;}
public final Target.MemberInner.InnerRec process() {class LocalHandler {Target.MemberInner.InnerRec createRec(String data) {return new Target() {@Overrideclass MemberInner {@Overrideclass InnerRec extends Target.MemberInner.InnerRec {InnerRec(String data) {super(data);}}}}.new MemberInner().new InnerRec(data);}}
Target.MemberInner.InnerRec rec = new LocalHandler().createRec("initial");int iterations = 0;
while (iterations < outerProtected) {rec.data += "_" + iterations;iterations++;}
try {Method method = Target.MemberInner.InnerRec.class.getMethod("fetch");List<String> result = (List<String>) method.invoke(rec);String superData = rec.superField; // Access super.field} catch (Exception e) {throw new RuntimeException("Processing failed: " + new Source().generateId());}
List<String> innerResult = super.fetch();return rec;}}
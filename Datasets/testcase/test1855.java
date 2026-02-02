package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.Arrays;import java.util.List;import java.util.function.Function;
public class SourceClass implements Processable {private String outerField = "source_data";
// Member inner classpublic class SourceMemberInner {String getOuterField() {return SourceClass.this.outerField;}}
// Anonymous inner classprivate Processable processor = new Processable() {@Overridepublic void process() {TargetClass.InnerRec rec = new TargetClass.InnerRec(1, "anon_data");try {varargsMethod(rec);} catch (Exception e) {}}};
public final List<String> varargsMethod(TargetClass.InnerRec... recs) throws Exception {List<String> result = new ArrayList<>();
// Generic method in array initialization (method reference)Function<TargetClass.InnerRec, TargetClass> mapper = OtherClass::createTarget;TargetClass[] targets = Arrays.stream(recs).map(mapper).toArray(TargetClass[]::new);
// Super keywordsfor (TargetClass target : targets) {if (target instanceof TargetParent) {((TargetParent) target).parentMethod();}}
// Used by reflectionMethod method = TargetClass.InnerRec.class.getMethod("value");for (TargetClass.InnerRec rec : recs) {result.add((String) method.invoke(rec));}
// Variable callresult.add("Count: " + recs.length);
// Access instance methodfor (TargetClass target : targets) {result.add(target.processRec(recs[0]));}
// Uses outer thisresult.add(new SourceMemberInner().getOuterField());
// If/else conditions with call method (static, method reference)if (recs.length > 0) {TargetClass processed = TargetClass::processStatic;result.add(processed.toString());} else {TargetClass defaultTarget = TargetClass.createDefault();result.add(defaultTarget.toString());}
return result;}
@Overridepublic void process() {processor.process();}}
interface Processable {void process();}
class TargetParent {public void parentMethod() {}}
/**
Target class with javadoc
Extends TargetParent and contains static nested class*/class TargetClass extends TargetParent {public String field;
public TargetClass(String data) {this.field = data;}
public record InnerRec(int id, String value) {}
// Static nested classpublic static class TargetStaticNested {public static String format(InnerRec rec) {return rec.id() + ":" + rec.value();}}
public String processRec(InnerRec rec) {return TargetStaticNested.format(rec);}
public static TargetClass createDefault() {return new TargetClass("default");}
public static TargetClass processStatic(InnerRec rec) {return new TargetClass(rec.value() + "_static");}}
class OtherClass {public static <T> TargetClass createTarget(TargetClass.InnerRec rec) {return new TargetClass(rec.value());}}
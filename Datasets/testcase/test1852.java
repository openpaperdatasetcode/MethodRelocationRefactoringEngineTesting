package test;
import java.util.ArrayList;import java.util.List;import java.util.function.IntFunction;
strictfp class SourceClass {// Member inner classpublic class SourceMemberInner {public record SourceInnerRec(String prefix) {/**
Processes variable arguments of TargetClass.InnerRec
@param recs Variable arguments of TargetClass.InnerRec
@return List of processed strings*/private List<String> varargsMethod(TargetClass.InnerRec... recs) {List<String> result = new ArrayList<>();rawList = new ArrayList(); // Raw type
// Labeled statementprocessing: {// Type declaration statementint count = 0;
// Enhanced for with break statementfor (TargetClass.InnerRec rec : recs) {if (rec == null) break processing;// Expression statementString combined = prefix + rec.value();result.add(combined);rawList.add(combined);count++;}}
// Abstract method references in constructor parameter listclass AbstractHandler {private final IntFunction<String> func1;private final IntFunction<String> func2;
AbstractHandler() {this.func1 = AbstractProcessor::process1;this.func2 = AbstractProcessor::process2;}
void handle(TargetClass.InnerRec rec) {result.add(func1.apply(rec.id()));result.add(func2.apply(rec.id()));}}AbstractHandler handler = new AbstractHandler();for (TargetClass.InnerRec rec : recs) {handler.handle(rec);}
// Private ConstructorInvocation with obj.field (same package)TargetClass.LocalCreator creator = new TargetClass.LocalCreator(recs[0].value());result.add(creator.create());
// Variable callresult.add("Total: " + recs.length);
return result;}
private ArrayList rawList;}}
// Local inner classpublic void useLocalInner() {class LocalProcessor {void process(TargetClass target) {TargetClass.InnerRec rec = new TargetClass.InnerRec(1, "data");new SourceMemberInner.SourceInnerRec("prefix_").varargsMethod(rec);}}new LocalProcessor().process(new TargetClass());}
private abstract static class AbstractProcessor {protected static String process1(int id) {return "Processed1: " + id;}
protected static String process2(int id) {return "Processed2: " + id;}}}
/**
Target class with javadoc
Contains local inner class and inner record*/class TargetClass {public record InnerRec(int id, String value) {}
{// Local inner classclass LocalValidator {boolean isValid(InnerRec rec) {return rec.id() > 0 && rec.value() != null;}}}
// LocalCreator for constructor invocationclass LocalCreator {private String base;
private LocalCreator(String base) {this.base = base;}
String create() {return "Created: " + base;}}}
package test;
import java.io.IOException;
strictfp class SourceClass {public static class StaticNested {public static void validateTarget(TargetClass target) throws IOException {if (target == null) {throw new IOException("Target cannot be null");}}}
public class MemberInner {/**
Varargs method to process multiple TargetClass instances
Handles inner class operations and validation
@param targets Varargs of TargetClass
@throws IOException If target is invalid*/@Deprecatedpublic void varargsMethod(TargetClass... targets) throws IOException {StaticNested.validateTarget(targets[0]);
for (TargetClass target : targets) {TargetClass.Inner inner = target.new Inner();inner.updateField(target.field);System.out.println(inner.getField());
if (inner.getField().length() > 20) {throw new IOException("Field exceeds max length");}}
this.varargsMethod(targets, true);}
public void varargsMethod(TargetClass[] targets, boolean log) throws IOException {if (log) {for (TargetClass target : targets) {System.out.println("Logging target field: " + target.field);}}}}}
private class TargetClass {String field;
public TargetClass(String field) {this.field = field;}
public class Inner {private String innerField;
public void updateField(String source) {this.innerField = source + "_inner";}
public String getField() {return innerField;}}}
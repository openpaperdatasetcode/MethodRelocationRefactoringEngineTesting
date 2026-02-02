package test;
public class SourceClass {private String outerPrivateField = "outerPrivate";static String outerStaticField = "outerStatic";
class SourceInner {abstract Object abstractMethod(TargetClass target);
public Object varargsMethod(TargetClass... targets) {int i = 0;while (i < targets.length) {this.varargsMethod(targets[i]);i++;}return null;}
@OverrideObject abstractMethod(TargetClass target) {String varCall = target.targetField;String privateAccess = outerPrivateField;String instanceField = target.targetField;String staticAccess = outerStaticField;String outerThis = SourceClass.this.outerPrivateField;return varCall + privateAccess + instanceField + staticAccess + outerThis;}}}
protected class TargetClass {String targetField = "targetField";
static class TargetStaticNested<T> {synchronized void genericMethod() {try {throw new Exception();} catch (Exception e) {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();inner.abstractMethod(new TargetClass());}}}}
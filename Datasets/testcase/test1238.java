package test.pkg;
protected class SourceClass {private String outerField = "outer";
public class MemberInnerClass {public void innerMethod() {}}
public static class StaticNestedClass {public static void nestedMethod() {}}
@Deprecatedpublic Object methodToMove(TargetClass targetParam, String value) {if (value != null) {synchronized (this) {TargetClass parentCall = new TargetClass(super.toString());MemberInnerClass inner = this.new MemberInnerClass();inner.innerMethod();StaticNestedClass.nestedMethod();return targetParam.process(super.hashCode(), this.outerField);}}return null;}
@Deprecatedpublic Object methodToMove(TargetClass targetParam) {return targetParam.process(super.hashCode(), null);}}
protected class TargetClass {private String parentData;
public TargetClass(Object parentArg) {this.parentData = super.toString() + parentArg;}
public Object process(int code, String data) {return code + ":" + data + ":" + parentData;}
// Method will be moved here:// @Deprecated// public Object methodToMove(TargetClass targetParam, String value) { ... }//// @Deprecated// public Object methodToMove(TargetClass targetParam) { ... }}
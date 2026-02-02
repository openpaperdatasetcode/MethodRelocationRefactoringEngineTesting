package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
protected class SourceClass {private int outerField = 10;
{new Runnable() {@Overridepublic void run() {}};}
@MyAnnotationprivate <T> void process(TargetClass.Inner.Rec... innerRecs) throws IOException {class LocalInner {void useOuterThis() {System.out.println(SourceClass.this.outerField);}}
static int count = TargetClass.STATIC_FIELD;if (count != 3) {throw new IOException("Static field mismatch");}
labeledLoop: {for (TargetClass.Inner.Rec rec : innerRecs) {rec.value = outerField;if (rec.value > 15) {break labeledLoop;}}}
new LocalInner().useOuterThis();}}
protected class TargetClass {static int STATIC_FIELD = 3;
class Inner {class Rec {int value;}}}
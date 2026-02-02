package test;
import java.io.IOException;import java.util.ArrayList;
interface SourceInterface {class MemberInnerClass {SourceInterface outerThis = SourceInterface.this;}
static class StaticNestedClass {static void staticMethod() {}}
protected TargetInterface.TargetInnerRec overridingMethod(TargetInterface.TargetInnerRec rec) throws IOException {try {static TargetInterface.TargetInnerRec staticRec = TargetInterface.STATIC_REC;String varCall = rec.data();
if (varCall.isEmpty()) {;return new TargetInterface.TargetInnerRec("empty");} else {ArrayList rawList = new ArrayList();rawList.add(rec);return staticRec;}} catch (NullPointerException e) {throw new IOException("Null rec encountered", e);}}}
/**
Protected target interface with javadoc
Contains local inner class and inner record*/protected interface TargetInterface {static final TargetInnerRec STATIC_REC = new TargetInnerRec("static_data");
record TargetInnerRec(String data) {}
default void createLocalInner() {class TargetLocalInner {String processRec(TargetInnerRec rec) {return rec.data().toUpperCase();}}TargetLocalInner local = new TargetLocalInner();local.processRec(STATIC_REC);}}
class Implementation implements SourceInterface, TargetInterface {@Overridepublic TargetInterface.TargetInnerRec overridingMethod(TargetInterface.TargetInnerRec rec) throws IOException {return SourceInterface.super.overridingMethod(rec);}
public void useCallMethod() {TargetInterface.TargetInnerRec testRec = new TargetInterface.TargetInnerRec("test");String result;
if (testRec.data().length() > 5) {result = StaticNestedClass::callMethod;} else {result = new MemberInnerClass()::callMethod;}}
static class StaticNestedClass {public static String callMethod(TargetInterface.TargetInnerRec rec) {return rec.data();}}
class MemberInnerClass {public String callMethod(TargetInterface.TargetInnerRec rec) {return rec.data();}}}
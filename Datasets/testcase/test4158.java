package test;
public class SourceClass {private TargetClass targetField = new TargetClass();private static final String SOURCE_STATIC_FIELD = "sourceStaticData";
@MyAnnotationprivate Object overloadingMethod(TargetClass.TargetInnerRec rec) {TargetClass.TargetInnerRec varCall = rec;String recData = varCall.getRecData();
if (!recData.contains(SOURCE_STATIC_FIELD)) {return new TargetClass.SubTargetClass().getDefaultData();}
TargetClass.TargetInnerRec[] recArray = {varCall, new TargetClass.TargetInnerRec("data2")};callVarargsMethod(recArray, SOURCE_STATIC_FIELD);return recData;}
@MyAnnotationprivate Object overloadingMethod(TargetClass.SubTargetClass subTarget) {String varCall = subTarget.getSubData();callVarargsMethod(new TargetClass.TargetInnerRec[]{new TargetClass.TargetInnerRec(varCall)}, SOURCE_STATIC_FIELD);return varCall;}
private void callVarargsMethod(TargetClass.TargetInnerRec... recs) {for (TargetClass.TargetInnerRec r : recs) {System.out.println(r.getRecData());}}
private void callVarargsMethod(TargetClass.TargetInnerRec[] recs, String staticField) {new TargetClass.SubTargetClass().printRecs(recs, staticField);}}
protected class TargetClass {public class TargetInnerRec {private String recData;
public TargetInnerRec(String recData) {this.recData = recData;}
public String getRecData() {return recData;}}
public static class SubTargetClass {public void printRecs(TargetInnerRec[] recs, String staticField) {for (TargetInnerRec r : recs) {System.out.println(r.getRecData() + "-" + staticField);}}
public String getSubData() {return "subTargetData";}
public String getDefaultData() {return "defaultData";}}}
@interface MyAnnotation {}
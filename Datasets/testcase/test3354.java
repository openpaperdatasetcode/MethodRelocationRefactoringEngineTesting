package test;
import java.util.ArrayList;import java.util.List;
@RefactorTestAnnotationpublic class SourceClass {private String outerPrivate1 = "privateVal1";private String outerPrivate2 = "privateVal2";private String outerPrivate3 = "privateVal3";
class MemberInner {class SourceInnerRec {public List<String> moveMethod (PublicTarget target, String... args) { this.fieldPublicTarget.StaticNested nested = new PublicTarget.StaticNested (target.thisField1,target.thisField2,target.thisField3);
List<String> result = new ArrayList<>();for (String arg : args) {String combined = arg + outerPrivate1 + outerPrivate2 + outerPrivate3;result.add (nested.process (combined));}return result;}}}
class LocalInnerHolder {void createLocal() {class LocalInner {}new LocalInner();}}}
public class PublicTarget {String thisField1 = "field1";String thisField2 = "field2";String thisField3 = "field3";
public static class StaticNested {private String nestedField1;private String nestedField2;private String nestedField3;
private StaticNested (String f1, String f2, String f3) {this.nestedField1 = f1;this.nestedField2 = f2;this.nestedField3 = f3;}
public String process(String input) {return input + nestedField1 + nestedField2 + nestedField3;}}}
@interface RefactorTestAnnotation {}
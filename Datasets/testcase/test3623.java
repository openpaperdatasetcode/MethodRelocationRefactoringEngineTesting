package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Field;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface EnumAnnot {}
interface TargetEnumInterface {}
public enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
protected String outerProtectedField = "enumProtectedData";
class MemberInner {void useOuterField() {System.out.println(outerProtectedField);}}
static class StaticNested {public static String staticField = "nestedStaticData";}
protected List<String> instanceMethod(TargetEnum target) {super();List<String> result = new ArrayList<>();
@EnumAnnotclass TypeDeclaration {}TypeDeclaration typeDecl = new TypeDeclaration();
public String fieldFromOther = OtherPackageClass.staticField;
try {TargetEnum chainResult = target.m1().m2().m3();this.outerProtectedField = chainResult.getInstanceField();result.add(outerProtectedField);} catch (Exception e) {e.printStackTrace();}
for (int i = 0; i < 3; i++) {variableCall(target);if (i == 1) break;}
try {Field reflectField = TargetEnum.class.getDeclaredField("instanceField");reflectField.setAccessible(true);result.add((String) reflectField.get(target));} catch (Exception e) {e.printStackTrace();}
return result;}
private void variableCall(TargetEnum target) {result.add(target.getInstanceField());}}
enum ExtendedSourceEnum implements SourceEnum {}
enum TargetEnum implements TargetEnumInterface {TARGET_INSTANCE;
private String instanceField = "targetEnumData";
public TargetEnum m1() {return this;}
public TargetEnum m2() {return this;}
public TargetEnum m3() {return this;}
String getInstanceField() {return instanceField;}}
class OtherPackageClass {public static String staticField = "otherPackageData";}
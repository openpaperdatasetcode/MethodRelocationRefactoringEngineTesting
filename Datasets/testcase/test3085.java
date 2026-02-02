import java.util.ArrayList;import java.util.List;
protected class SourceClass<T> {protected String outerProtected = "protected_data";public static String staticField = "source_static";
static class StaticNested {}
class MemberInner {record InnerRecord(TargetClass target) {public abstract TargetClass process();}}
public TargetClass processTarget(TargetClass target) {super();TypeDeclaration typeDecl = new TypeDeclaration();
// Access outer protected fieldtarget.new MemberInner().setValue(outerProtected);
// Raw type usageList rawList = new ArrayList();rawList.add(target);
// Variable call + depend on static fieldtarget.new MemberInner().processStatic(SourceClass.staticField);
// Use inner record (source_inner_rec position)MemberInner.InnerRecord record = new MemberInner.InnerRecord(target) {@Overridepublic TargetClass process() {return target;}};
return record.process();}
class TypeDeclaration {}}
private class TargetClass {class MemberInner {private String value;
void setValue(String val) {this.value = val;}
void processStatic(String staticData) {this.value = staticData;}}}
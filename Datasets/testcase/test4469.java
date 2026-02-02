package test;
enum SourceClass {INSTANCE;
class MemberInner1 {}class MemberInner2 {}
TargetClass targetField = TargetClass.VALUE;
@Deprecatedfinal TargetClass method(String... args) {class LocalType {}LocalType local = new LocalType();TargetClass result = accessInstanceMethod();return args.length > 0 ? new TargetClass() {}.protectedMethod() : result;}
private TargetClass accessInstanceMethod() {return targetField;}}
enum TargetClass {VALUE;
Object protectedMethod() {return new Object();}}
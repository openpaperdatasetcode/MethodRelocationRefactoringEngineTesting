package test;
class SourceClass extends ParentSource {private String outerPrivateField = "privateData";
static class StaticNested {}class SourceInner {private <T> Object moveMethod(TargetClass<String> param, Object... args) {class LocalType {}LocalType localObj = new LocalType();
if (args.length > 0) {param.staticNested.process(outerPrivateField);param.inner.innerRec.action(args);}return localObj;}}}
class ParentSource {}
class TargetClass<T> {public TargetInner inner = new TargetInner();public static class StaticNested {void process(String data) {}}
class TargetInner {class TargetInnerRec {void action(Object... params) {}}}}